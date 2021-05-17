package id.rezajuliandri.amegu.ui.search.result;

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

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentSearchResultBinding;
import id.rezajuliandri.amegu.ui.home.HomePetAdapter;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.ItemDetailAbstract;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;


public class SearchResultFragment extends ItemDetailAbstract {
    FragmentSearchResultBinding binding;
    SearchResultViewModel viewModel;
    HomePetAdapter homePetAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchResultBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupActionBar();
        getData();
    }

    private void getData() {
        binding.toolbar.searchBox.clearFocus();
        binding.toolbar.searchBox.setFocusableInTouchMode(false);

        binding.rvMainContent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        homePetAdapter = new HomePetAdapter(requireActivity().getApplication(), this, binding.getRoot());

        String keyword = SearchResultFragmentArgs.fromBundle(requireArguments()).getKeyword();
        String hasilPencarian = requireActivity().getString(R.string.hasil_pencarian) + " " + keyword;
        binding.textResult.setText(hasilPencarian);

        viewModel.searchData(keyword);
        viewModel.searchPet(keyword).observe(getViewLifecycleOwner(), pets -> {
            if (pets != null) {
                switch (pets.status) {
                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.progressBar.setVisibility(View.GONE);
                        if (pets.data.size() == 0) {
                            binding.emptyResponse.setVisibility(View.VISIBLE);
                        }
                        homePetAdapter.setData(pets.data);
                        binding.rvMainContent.setAdapter(homePetAdapter);
                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    private void setupActionBar() {
        ActionBarHelper actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();

        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
    }

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(SearchResultViewModel.class);
    }

    @Override
    public void onResume() {
        super.onResume();
        EditText editText = binding.toolbar.searchBox;
        editText.clearFocus();
        editText.setFocusableInTouchMode(false);
    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {
        SearchResultFragmentDirections.ActionSearchResultFragmentToPetDetailFragment toPetDetailFragment = SearchResultFragmentDirections.actionSearchResultFragmentToPetDetailFragment(pet.getId());
        toPetDetailFragment.setPetId(pet.getId());
        Navigation.findNavController(view).navigate(toPetDetailFragment);
    }

    @Override
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_searchResultFragment_to_searchFragment);
    }
}