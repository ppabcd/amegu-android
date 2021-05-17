package id.rezajuliandri.amegu.ui.auth.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.ActivityRegisterBinding;
import id.rezajuliandri.amegu.ui.auth.login.LoginActivity;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class RegisterActivity extends AppCompatActivity {
    ActivityRegisterBinding binding;
    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupActionBar();
        setupViewModel();
        viewAction();
    }
    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean registerEnabled = true;
            viewModel.registerDataChanged(
                    binding.firstName.getText().toString(),
                    binding.lastName.getText().toString(),
                    binding.username.getText().toString(),
                    binding.password.getText().toString(),
                    binding.phoneNumber.getText().toString()
            );
            String checkUsername = viewModel.getErrorMsg(viewModel.USERNAME);
            String checkPassword = viewModel.getErrorMsg(viewModel.PASSWORD);
            String checkFirstName = viewModel.getErrorMsg(viewModel.FIRST_NAME);
            String checkLastName = viewModel.getErrorMsg(viewModel.LAST_NAME);
            String checkPhoneNumber = viewModel.getErrorMsg(viewModel.PHONE_NUMBER);
            binding.register.setEnabled(false);

            if (checkFirstName != null) {
                binding.firstName.setError(checkFirstName);
                registerEnabled = false;
            }
            if (checkLastName != null) {
                binding.lastName.setError(checkLastName);
                registerEnabled = false;
            }
            if (checkUsername != null) {
                binding.username.setError(checkUsername);
                registerEnabled = false;
            }
            if (checkPassword != null) {
                binding.password.setError(checkPassword);
                registerEnabled = false;
            }
            if(checkPhoneNumber != null){
                binding.phoneNumber.setError(checkPhoneNumber);
                registerEnabled = false;
            }
            binding.register.setEnabled(registerEnabled);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    private void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(RegisterViewModel.class);
    }

    private void viewAction() {
        binding.actionLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
        binding.firstName.addTextChangedListener(textWatcher);
        binding.lastName.addTextChangedListener(textWatcher);
        binding.username.addTextChangedListener(textWatcher);
        binding.password.addTextChangedListener(textWatcher);
        binding.phoneNumber.addTextChangedListener(textWatcher);
        binding.register.setOnClickListener(v->{
            binding.register.setEnabled(false);
            binding.register.setText(R.string.loading);
            viewModel.register(
                    binding.firstName.getText().toString().trim(),
                    binding.lastName.getText().toString().trim(),
                    binding.username.getText().toString().trim().toLowerCase(),
                    binding.password.getText().toString().trim(),
                    binding.phoneNumber.getText().toString().trim()
            ).observe(this, status ->{
                if(status.toLowerCase().equals("ok")){
                    Toast.makeText(this, "Berhasil membuat akun", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                    return;
                }
                binding.register.setEnabled(true);
                binding.register.setText(R.string.action_register);
                Toast.makeText(this, "Gagal membuat akun", Toast.LENGTH_SHORT).show();
            });
        });
    }

    private void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Melakukan disable pada night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

}