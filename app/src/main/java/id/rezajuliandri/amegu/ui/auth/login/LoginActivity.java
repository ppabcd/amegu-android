package id.rezajuliandri.amegu.ui.auth.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import id.rezajuliandri.amegu.databinding.ActivityLoginBinding;
import id.rezajuliandri.amegu.ui.auth.address.AddressActivity;
import id.rezajuliandri.amegu.ui.auth.register.RegisterActivity;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.utils.BaseActivity;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class LoginActivity extends BaseActivity {
    ActivityLoginBinding binding;
    LoginViewModel viewModel;
    TextWatcher textListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            boolean loginEnabled = true;
            viewModel.loginDataChanged(
                    binding.username.getText().toString(),
                    binding.password.getText().toString()
            );
            String checkUsername = viewModel.getErrorMsg(viewModel.USERNAME);
            String checkPassword = viewModel.getErrorMsg(viewModel.PASSWORD);
            if (checkUsername != null) {
                binding.username.setError(checkUsername);
                loginEnabled = false;
            }
            if (checkPassword != null) {
                binding.password.setError(checkPassword);
                loginEnabled = false;
            }
            binding.login.setEnabled(loginEnabled);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
    boolean isLock = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeValidateToken(false);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupActionBar();
        setupViewModel();
        viewAction();
    }

    private void viewAction() {
        binding.username.addTextChangedListener(textListener);
        binding.password.addTextChangedListener(textListener);
        binding.actionRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
        binding.login.setOnClickListener(v -> {
            binding.login.setEnabled(false);
            viewModel.login(
                    binding.username.getText().toString(),
                    binding.password.getText().toString()
            ).observe(this, userEntityResource -> {
                if (userEntityResource != null) {
                    String btnText = "Masuk";
                    switch (userEntityResource.status) {
                        case LOADING:
                            btnText = "Loading...";
                            break;
                        case SUCCESS:
                            if (userEntityResource.data != null) {
                                if (userEntityResource.data.getToken() == null) {
                                    Toast.makeText(
                                            LoginActivity.this,
                                            "Username / Password salah. Periksa juga koneksi internet.",
                                            Toast.LENGTH_SHORT
                                    ).show();
                                    return;
                                }
                                setProfile(Objects.requireNonNull(userEntityResource.data.getToken()));
                                btnText = "Masuk";
                                binding.login.setEnabled(true);
                            }
                            break;
                        case ERROR:
                            AlertDialog.Builder builder = new AlertDialog.Builder(this);
                            builder.setTitle(userEntityResource.message);
                            builder.setPositiveButton("Ok", ((dialog, which) -> {
                            }));
                            builder.show();
                            btnText = "Masuk";
                            binding.login.setEnabled(true);
                            break;

                        default:
                            btnText = "Masuk";
                    }
                    binding.login.setText(btnText);
                }
            });
        });
    }

    private void setProfile(String token) {
        binding.login.setEnabled(false);
        viewModel.setProfile(token).observe(this, profile -> {
            if (profile != null) {
                String btnText = "Masuk";
                switch (profile.status) {
                    case LOADING:
                        btnText = "Loading...";
                        break;
                    case SUCCESS:
                        if (!isLock) {
                            if (profile.data != null) {
                                isLock = true;
                                if (profile.data.getAlamatId() == 0) {
                                    Intent intent = new Intent(LoginActivity.this, AddressActivity.class);
                                    startActivity(intent);
                                    finish();
                                    return;
                                }
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                binding.login.setEnabled(true);
                                btnText = "Masuk";
                            }
                        }
                        break;
                    case ERROR:
                        Toast.makeText(LoginActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        binding.login.setEnabled(true);
                        btnText = "Masuk";
                        break;

                    default:
                        btnText = "Masuk";
                        binding.login.setEnabled(true);
                }
                binding.login.setText(btnText);
            }
        });
    }

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(LoginViewModel.class);
    }

    private void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Melakukan disable pada night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
}