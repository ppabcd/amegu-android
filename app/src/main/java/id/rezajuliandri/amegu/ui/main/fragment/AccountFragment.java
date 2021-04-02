package id.rezajuliandri.amegu.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.interfaces.auth.OnLogout;
import id.rezajuliandri.amegu.viewmodel.LoginViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.LoginViewModelFactory;

public class AccountFragment extends Fragment {
    Button logoutBtn;
    LoginViewModel loginViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        // View model
        loginViewModel = new ViewModelProvider(
                this,
                new LoginViewModelFactory(requireActivity().getApplication())
        ).get(LoginViewModel.class);

        logoutBtn = view.findViewById(R.id.logout);

        // Logout
        logoutBtn.setOnClickListener(v -> {
            loginViewModel.logout(new OnLogout() {
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
        return view;
    }
}