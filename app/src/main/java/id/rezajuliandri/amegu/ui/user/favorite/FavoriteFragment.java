package id.rezajuliandri.amegu.ui.user.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentFavoriteBinding;
import id.rezajuliandri.amegu.ui.home.HomePetAdapter;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.ItemDetailAbstract;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class FavoriteFragment extends ItemDetailAbstract {
    FragmentFavoriteBinding binding;
    FavoriteViewModel viewModel;
    HomePetAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBar();
        setViewModel();
        getData();
    }

    private void getData() {
        binding.searchResult.setVisibility(View.VISIBLE);
        binding.textResult.setText(R.string.favorite);

        binding.rvMainContent.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        adapter = new HomePetAdapter(requireActivity().getApplication(), this, binding.getRoot());

        viewModel.getPetFavorites().observe(getViewLifecycleOwner(), petEntities -> {
            binding.emptyResponse.setVisibility(View.GONE);
            if (petEntities != null) {
                binding.progressBar.setVisibility(View.GONE);
                adapter.setData(petEntities);
                adapter.notifyDataSetChanged();
                binding.rvMainContent.setAdapter(adapter);
                if (petEntities.size() == 0) {
                    binding.emptyResponse.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(FavoriteViewModel.class);
    }

    private void setActionBar() {
        ActionBarHelper actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
        binding.toolbar.searchBox.clearFocus();
        binding.toolbar.searchBox.setFocusableInTouchMode(false);
    }

    @Override
    public void moveToDetailPet(View view, PetEntity pet) {
        FavoriteFragmentDirections.ActionFavoriteFragmentToPetDetailFragment toPetDetailFragment = FavoriteFragmentDirections.actionFavoriteFragmentToPetDetailFragment(pet.getId());
        Navigation.findNavController(binding.getRoot()).navigate(toPetDetailFragment);
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