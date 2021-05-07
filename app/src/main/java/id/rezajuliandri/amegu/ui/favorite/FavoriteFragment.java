package id.rezajuliandri.amegu.ui.favorite;

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
import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.data.entity.pet.Favorite;
import id.rezajuliandri.amegu.data.entity.pet.Pet;
import id.rezajuliandri.amegu.databinding.FragmentHomeBinding;
import id.rezajuliandri.amegu.helpers.ActionBarHelper;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import id.rezajuliandri.amegu.interfaces.pet.OnAllFavorites;
import id.rezajuliandri.amegu.interfaces.pet.OnPet;
import id.rezajuliandri.amegu.ui.search_result.SearchResultFragmentDirections;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;


public class FavoriteFragment extends ItemDetailAbstract {
    FavoriteViewModel favoriteViewModel;
    FragmentHomeBinding binding;
    ActionBarHelper actionBarHelper;
    Session session;
    PetAdapter petAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Menggunakan custom actionbar
        actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        ActionBarHelper.searchLayoutHandler(view, this);
        binding.toolbar.searchBox.clearFocus();
        binding.toolbar.searchBox.setFocusableInTouchMode(false);

        // View Model
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireActivity().getApplication());
        favoriteViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(FavoriteViewModel.class);
        session = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(Session.class);


        binding.searchResult.setVisibility(View.VISIBLE);
        binding.textResult.setText(R.string.favorite);
        binding.banner.setVisibility(View.GONE);

        // Recycler View
        binding.rvMainContent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        petAdapter = new PetAdapter(requireActivity().getApplication(), this, binding.getRoot());

        // Get database on favorite and search pet
        session.getToken(new OnToken() {
            @Override
            public void success(String token) {
                favoriteViewModel.getFavorites(new OnPet() {
                    @Override
                    public void success(List<Pet> pet) {
                        petAdapter.setData(pet);
                        binding.rvMainContent.setAdapter(petAdapter);
                    }

                    @Override
                    public void successWithPagination(List<Pet> pet, Pagination pagination) {

                    }

                    @Override
                    public void error(String error) {
                        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show();
                    }
                }, token);
            }

            @Override
            public void error(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void moveToDetailPet(View view, Pet pet) {
        FavoriteFragmentDirections.ActionFavoriteFragmentToPetDetailFragment toPetDetailFragment = FavoriteFragmentDirections.actionFavoriteFragmentToPetDetailFragment(pet);
        toPetDetailFragment.setPet(pet);
        Navigation.findNavController(view).navigate(toPetDetailFragment);
    }

    @Override
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_favoriteFragment_to_searchFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }
}