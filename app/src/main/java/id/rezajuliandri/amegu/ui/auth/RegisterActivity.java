package id.rezajuliandri.amegu.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.ActivityRegisterBinding;
import id.rezajuliandri.amegu.interfaces.auth.OnRegister;
import id.rezajuliandri.amegu.ui.middleware.BaseActivity;
import id.rezajuliandri.amegu.viewmodel.RegisterViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.RegisterViewModelFactory;

public class RegisterActivity extends BaseActivity {

    RegisterViewModel registerViewModel;
    ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeValidateToken(false);
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Objects.requireNonNull(getSupportActionBar()).hide();

        // View Model
        registerViewModel = new ViewModelProvider(
                this,
                new RegisterViewModelFactory(this.getApplication())
        ).get(RegisterViewModel.class);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerViewModel.registerDataChanged(binding.firstName.getText().toString(),
                        binding.lastName.getText().toString(),
                        binding.username.getText().toString(),
                        binding.password.getText().toString());
                String checkUsername = registerViewModel.getErrorMsg(registerViewModel.USERNAME);
                String checkPassword = registerViewModel.getErrorMsg(registerViewModel.PASSWORD);
                String checkFirstName = registerViewModel.getErrorMsg(registerViewModel.FIRST_NAME);
                String checkLastName = registerViewModel.getErrorMsg(registerViewModel.LAST_NAME);
                binding.register.setEnabled(false);

                if (checkFirstName != null) {
                    binding.firstName.setError(checkFirstName);
                    return;
                }
                if (checkLastName != null) {
                    binding.lastName.setError(checkLastName);
                    return;
                }
                if (checkUsername != null) {
                    binding.username.setError(checkUsername);
                    return;
                }
                if (checkPassword != null) {
                    binding.password.setError(checkPassword);
                    return;
                }
                binding.register.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        binding.firstName.addTextChangedListener(afterTextChangedListener);
        binding.lastName.addTextChangedListener(afterTextChangedListener);
        binding.username.addTextChangedListener(afterTextChangedListener);
        binding.password.addTextChangedListener(afterTextChangedListener);


        // If button register clicked
        binding.register.setOnClickListener(v -> {
            binding.register.setEnabled(false);
            binding.register.setText(R.string.loading);
            registerViewModel.register(
                    new OnRegister() {
                        @Override
                        public void success() {
                            Toast.makeText(RegisterActivity.this, "Successfully created an account", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void error(String message) {
                            Toast.makeText(RegisterActivity.this, "Failed created an account", Toast.LENGTH_SHORT).show();
                            binding.register.setText(R.string.action_register);
                            binding.register.setEnabled(true);
                        }
                    },
                    binding.firstName.getText().toString().trim(),
                    binding.lastName.getText().toString().trim(),
                    binding.username.getText().toString().trim().toLowerCase(),
                    binding.password.getText().toString().trim()
            );
        });
        // If button action login clicked
        binding.actionLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}