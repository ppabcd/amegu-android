package id.rezajuliandri.amegu.ui.home;

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
import id.rezajuliandri.amegu.databinding.FragmentHomeBinding;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.ItemDetailAbstract;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class HomeFragment extends ItemDetailAbstract {
    FragmentHomeBinding binding;
    HomePetAdapter petAdapter;
    HomeViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setActionBar();
        setAdapter();
        setViewModel();
        getData();
    }

    private void setActionBar() {
        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
    }

    private void getData() {
        viewModel.getPets().observe(requireActivity(), pets -> {
            if (pets != null) {
                switch (pets.status) {
                    case LOADING:
                        binding.progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        binding.progressBar.setVisibility(View.GONE);
                        binding.emptyResponse.setVisibility(View.GONE);
                        if(pets.data != null){
                            if (pets.data.size() == 0) {
                                binding.emptyResponse.setVisibility(View.VISIBLE);
                            }
                            petAdapter.setData(pets.data);
                            binding.rvMainContent.setAdapter(petAdapter);
                        }
                        break;
                    case ERROR:
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(requireContext(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        break;

                }
            }
        });
    }

    private void setViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(HomeViewModel.class);
    }

    private void setAdapter() {
        binding.rvMainContent.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        petAdapter = new HomePetAdapter(requireActivity().getApplication(), this, binding.getRoot());
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
        HomeFragmentDirections.ActionNavigationHomeToPetDetailFragment toPetDetailFragment = HomeFragmentDirections.actionNavigationHomeToPetDetailFragment(pet.getId());
        toPetDetailFragment.setPetId(pet.getId());
        Navigation.findNavController(view).navigate(toPetDetailFragment);
    }

    @Override
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_searchFragment);
    }
}