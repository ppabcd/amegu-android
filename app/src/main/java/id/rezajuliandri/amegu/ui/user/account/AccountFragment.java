package id.rezajuliandri.amegu.ui.user.account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.FragmentAccountBinding;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class AccountFragment extends Fragment {
    FragmentAccountBinding binding;
    AccountViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAccountBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewModel();
        getData();
    }

    private void getData() {
        viewModel.getUser().observe(getViewLifecycleOwner(), userEntity -> {
            if (userEntity != null) {
                String fullName = userEntity.getFirstName() + " " + userEntity.getLastName();
                binding.name.setText(fullName);
                viewModel.getAlamat(userEntity.getAlamatId()).observe(getViewLifecycleOwner(), alamatEntity -> {
                    binding.location.setText(alamatEntity.getProvinsiName());
                });
                if(userEntity.getIsAdmin() == 1){
                    binding.btnPayment.setOnClickListener(v ->{
                        Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_userPaymentFragment);
                    });
                } else {
                    binding.btnPayment.setVisibility(View.GONE);
                }
            }
        });

        binding.btnAccount.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_accountDetailFragment);
        });
        binding.btnAddress.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_addressUserFragment);
        });
        binding.btnAdoption.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_userPetAdoptionFragment);
        });
        binding.btnBankAccount.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_bankAccountFragment);
        });
        binding.btnPets.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_petsUserFragment);
        });
        binding.btnFavorite.setOnClickListener(v -> {
            Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_favoriteFragment);
        });
        binding.logout.setOnClickListener(v -> {
            viewModel.logout().observe(getViewLifecycleOwner(), status -> {
                if (status.toLowerCase().equals("ok")) {
                    Navigation.findNavController(binding.getRoot()).navigate(R.id.action_navigation_account_to_loginActivity);
                    getActivity().finish();
                }
            });
        });
    }

    private void setViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(requireContext());
        viewModel = new ViewModelProvider(this,
                viewModelFactory
        ).get(AccountViewModel.class);

    }
}