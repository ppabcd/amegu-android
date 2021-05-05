package id.rezajuliandri.amegu.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.abstraction.ItemDetailAbstract;
import id.rezajuliandri.amegu.adapter.PetAdapter;
import id.rezajuliandri.amegu.data.entity.Pagination;
import id.rezajuliandri.amegu.databinding.FragmentHomeBinding;
import id.rezajuliandri.amegu.data.entity.pet.Pet;
import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.helpers.ActionBarHelper;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import id.rezajuliandri.amegu.interfaces.pet.OnPet;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class HomeFragment extends ItemDetailAbstract {
    PetAdapter petAdapter;

    HomeViewModel homeViewModel;
    Session session;

    FragmentHomeBinding fragmentHomeBinding;

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomeBinding = FragmentHomeBinding.inflate(getLayoutInflater());
        ActionBarHelper.searchLayoutHandler(fragmentHomeBinding.getRoot(), this);
        return fragmentHomeBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Declare view

        // Recyclerview settings
        fragmentHomeBinding.rvMainContent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        petAdapter = new PetAdapter(requireActivity().getApplication(), this, fragmentHomeBinding.getRoot());

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getActivity().getApplication());
        homeViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(HomeViewModel.class);
        session = new Session(getActivity().getApplication());

        // Just dummy data
        // TODO Delete this code after connected to API
        session.getToken(new OnToken() {
            @Override
            public void success(String token) {
                homeViewModel.getPet(token, new OnPet() {
                    @Override
                    public void success(List<Pet> pet) {
                        petAdapter.setData(pet);
                        fragmentHomeBinding.rvMainContent.setAdapter(petAdapter);
                    }

                    @Override
                    public void successWithPagination(List<Pet> pet, Pagination pagination) {

                    }

                    @Override
                    public void error(String error) {

                    }
                });
            }

            @Override
            public void error(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        // END Dummy
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = fragmentHomeBinding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    /**
     * Memindahkan ke fragment search
     *
     * @param view
     */
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment);
    }

    /**
     * Memindahkan ke fragment pet detail
     *
     * @param view
     * @param pet  Data pet yang ingin ditampilkan data detailnya
     */
    @Override
    public void moveToDetailPet(View view, Pet pet) {
        HomeFragmentDirections.ActionNavigationHomeToPetDetailFragment toPetDetailFragment = HomeFragmentDirections.actionNavigationHomeToPetDetailFragment(pet);
        toPetDetailFragment.setPet(pet);
        Navigation.findNavController(view).navigate(toPetDetailFragment);
    }

}