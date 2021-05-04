package id.rezajuliandri.amegu.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.FragmentAccountBinding;
import id.rezajuliandri.amegu.entity.Alamat;
import id.rezajuliandri.amegu.entity.Session;
import id.rezajuliandri.amegu.entity.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnAlamat;
import id.rezajuliandri.amegu.interfaces.auth.OnLogout;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;
import id.rezajuliandri.amegu.viewmodel.LoginViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.LoginViewModelFactory;

public class AccountFragment extends Fragment {
    LoginViewModel loginViewModel;
    Session session;
    FragmentAccountBinding binding;

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

        // View model
        loginViewModel = new ViewModelProvider(
                this,
                new LoginViewModelFactory(requireActivity().getApplication())
        ).get(LoginViewModel.class);
        session = new Session(requireActivity().getApplication());

        // Pengambilan data user dari database lokal
        session.getUser(new OnProfile() {
            @Override
            public void success(Users users) {
                String fullName = users.getFirstName() + " " + users.getLastName();
                binding.name.setText(fullName);
            }

            @Override
            public void error(String message) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            }
        });
        session.getAlamat(new OnAlamat() {

            @Override
            public void success(Alamat alamat) {
                binding.location.setText(alamat.getProvinsiName());
            }

            @Override
            public void error(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        // Jika button account di klik
        binding.btnAccount.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_navigation_account_to_accountDetailFragment);
        });
        // Jika button address di klik
        binding.btnAddress.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_navigation_account_to_addressFragment);
        });
        // Jika button adoption di klik
        binding.btnAdoption.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_navigation_account_to_transactionFragment);
        });

        // Proses logout dari sistem dan akan menghapus user data dari database lokal
        binding.logout.setOnClickListener(v -> {
            session.logout(new OnLogout() {
                @Override
                public void success() {
                    Toast.makeText(requireContext(), "Berhasil logout", Toast.LENGTH_SHORT).show();
                    Navigation.findNavController(view).navigate(R.id.action_navigation_account_to_loginActivity);
                }

                @Override
                public void error(String message) {
                    Toast.makeText(requireContext(), "Gagal logout", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}