package id.rezajuliandri.amegu.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.ActivityLoginBinding;
import id.rezajuliandri.amegu.data.entity.auth.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnLogin;
import id.rezajuliandri.amegu.ui.address.AddressActivity;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.middleware.BaseActivity;
import id.rezajuliandri.amegu.ui.register.RegisterActivity;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class LoginActivity extends BaseActivity {
    LoginViewModel loginViewModel;
    ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        changeValidateToken(false);
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Objects.requireNonNull(getSupportActionBar()).hide();

        // View Model
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this.getApplication());
        loginViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(LoginViewModel.class);


        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginViewModel.loginDataChanged(activityLoginBinding.username.getText().toString(),
                        activityLoginBinding.password.getText().toString());
                String checkUsername = loginViewModel.getErrorMsg(loginViewModel.USERNAME);
                String checkPassword = loginViewModel.getErrorMsg(loginViewModel.PASSWORD);
                if (checkUsername != null) {
                    activityLoginBinding.username.setError(checkUsername);
                    activityLoginBinding.login.setEnabled(false);
                    return;
                }
                if (checkPassword != null) {
                    activityLoginBinding.password.setError(checkPassword);
                    activityLoginBinding.login.setEnabled(false);
                    return;
                }
                activityLoginBinding.login.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        activityLoginBinding.username.addTextChangedListener(afterTextChangedListener);
        activityLoginBinding.password.addTextChangedListener(afterTextChangedListener);

        activityLoginBinding.login.setOnClickListener(v -> {
            activityLoginBinding.login.setEnabled(false);
            activityLoginBinding.login.setText(R.string.loading);
            // Request login to server
            loginViewModel.login(
                    new OnLogin() {
                        @Override
                        public void success(Users user) {
                            // if true, continue to main activity
                            Intent intent = (user.getAlamatId() == 0) ?
                                    new Intent(
                                            LoginActivity.this,
                                            AddressActivity.class) :
                                    new Intent(
                                            LoginActivity.this,
                                            MainActivity.class);
//                            Intent intent  = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void error(String message) {
                            activityLoginBinding.login.setEnabled(true);
                            activityLoginBinding.login.setText(R.string.action_sign_in);
                            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                            dialog.setTitle("Something went wrong");
                            dialog.setMessage(message);
                            dialog.setPositiveButton("Ok", (dialog1, which) -> {

                            });
                            dialog.show();
                        }
                    },
                    activityLoginBinding.username.getText().toString().trim().toLowerCase(),
                    activityLoginBinding.password.getText().toString().trim()
            );
        });
        activityLoginBinding.actionRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}