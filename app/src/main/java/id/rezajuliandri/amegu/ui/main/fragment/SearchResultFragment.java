package id.rezajuliandri.amegu.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.adapter.PetAdapter;
import id.rezajuliandri.amegu.api.responses.data.Pagination;
import id.rezajuliandri.amegu.databinding.FragmentHomeBinding;
import id.rezajuliandri.amegu.entity.Pet;
import id.rezajuliandri.amegu.entity.SearchHistory;
import id.rezajuliandri.amegu.entity.Session;
import id.rezajuliandri.amegu.helper.ActionBarHelper;
import id.rezajuliandri.amegu.helper.ActionBarSearchHelper;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import id.rezajuliandri.amegu.interfaces.pet.OnPet;
import id.rezajuliandri.amegu.ui.main.abstraction.ItemDetailAbstract;
import id.rezajuliandri.amegu.viewmodel.HomeViewModel;
import id.rezajuliandri.amegu.viewmodel.SearchViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.HomeViewmodelFactory;
import id.rezajuliandri.amegu.viewmodel.factory.SearchViewModelFactory;

public class SearchResultFragment extends ItemDetailAbstract  {
    SearchViewModel searchViewModel;
    ActionBarHelper actionBarHelper;

    HomeViewModel homeViewModel;
    Session session;

    PetAdapter petAdapter;

    FragmentHomeBinding binding;

    public SearchResultFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        session = new Session(getActivity().getApplication());
        // View Model
        searchViewModel = new ViewModelProvider(
                this,
                new SearchViewModelFactory(requireActivity().getApplication())
        ).get(SearchViewModel.class);
        homeViewModel = new ViewModelProvider(
                this,
                new HomeViewmodelFactory(requireActivity().getApplication())
        ).get(HomeViewModel.class);


        binding.rvMainContent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        petAdapter = new PetAdapter(requireActivity().getApplication(), this, binding.getRoot());


        binding.searchResult.setVisibility(View.VISIBLE);
        binding.banner.setVisibility(View.GONE);


        String keyword = SearchResultFragmentArgs.fromBundle(requireArguments()).getKeyword();
        String hasilPencarian = requireActivity().getString(R.string.hasil_pencarian) + " " + keyword;
        binding.textResult.setText(hasilPencarian);

        // Menggunakan custom actionbar
        actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();

        ActionBarHelper.searchLayoutHandler(view, this);

        binding.toolbar.searchBox.clearFocus();
        binding.toolbar.searchBox.setFocusableInTouchMode(false);

        SearchHistory searchHistory = new SearchHistory(keyword);
        searchViewModel.updateOrInsert(keyword, searchHistory);

        session.getToken(new OnToken() {
            @Override
            public void success(String token) {
                homeViewModel.searchPet(keyword, token, new OnPet() {
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

                    }
                });
            }

            @Override
            public void error(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Proses pindah fragment ke searchFragment
     * @param view
     */
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment);
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    @Override
    public void moveToDetailPet(View view, Pet pet) {
        SearchResultFragmentDirections.ActionSearchResultFragmentToPetDetailFragment toPetDetailFragment = SearchResultFragmentDirections.actionSearchResultFragmentToPetDetailFragment(pet);
        toPetDetailFragment.setPet(pet);
        Navigation.findNavController(view).navigate(toPetDetailFragment);
    }
}