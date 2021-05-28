package id.rezajuliandri.amegu.ui.user.pets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.databinding.FragmentPetsUserBinding;
import id.rezajuliandri.amegu.ui.home.HomePetAdapter;
import id.rezajuliandri.amegu.utils.ActionBarHelper;
import id.rezajuliandri.amegu.utils.BaseFragment;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;


public class PetsUserFragment extends BaseFragment {
    FragmentPetsUserBinding binding;
    PetUserViewModel viewModel;
    HomePetAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPetsUserBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    protected void getData() {
        binding.searchResult.setVisibility(View.VISIBLE);
        binding.textResult.setText(R.string.hewan_saya);

        binding.rvMainContent.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        adapter = new HomePetAdapter(requireActivity().getApplication(), this, binding.getRoot());

        viewModel.getUserData().observe(getViewLifecycleOwner(), userEntity -> {
            if (userEntity != null) {
                viewModel.getMyPets(userEntity.getToken()).observe(getViewLifecycleOwner(), pets -> {
                    if (pets != null) {
                        switch (pets.status) {
                            case LOADING:
                                binding.progressBar.setVisibility(View.VISIBLE);
                                break;
                            case SUCCESS:
                                binding.progressBar.setVisibility(View.GONE);
                                if (pets.data != null) {
                                    if (pets.data.size() == 0) {
                                        binding.emptyResponse.setVisibility(View.VISIBLE);
                                    }
                                    adapter.setData(pets.data);
                                    binding.rvMainContent.setAdapter(adapter);
                                }
                                break;
                            case ERROR:
                                binding.progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                                break;

                        }
                    }
                });
            }
        });
    }

    @Override
    protected void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(getContext());
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(PetUserViewModel.class);
    }

    @Override
    protected void setActionBar() {
        ActionBarHelper actionBarHelper = new ActionBarHelper(getActivity(), binding.getRoot());
        actionBarHelper.showBackButton();
        ActionBarHelper.searchLayoutHandler(binding.getRoot(), this);
        binding.toolbar.searchBox.clearFocus();
        binding.toolbar.searchBox.setFocusableInTouchMode(false);
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
        PetsUserFragmentDirections.ActionPetsUserFragmentToPetDetailFragment toPetDetailFragment = PetsUserFragmentDirections.actionPetsUserFragmentToPetDetailFragment(pet.getId());
        Navigation.findNavController(binding.getRoot()).navigate(toPetDetailFragment);
    }

    @Override
    protected void moveToSearchFragment(View view) {
        Navigation.findNavController(view).navigate(R.id.action_petsUserFragment_to_searchFragment);
    }
}