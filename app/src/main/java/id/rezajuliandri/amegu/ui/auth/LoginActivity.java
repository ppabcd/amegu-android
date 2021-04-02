package id.rezajuliandri.amegu.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.entity.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnLogin;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.viewmodel.LoginViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button loginBtn, actionRegister;
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Objects.requireNonNull(getSupportActionBar()).hide();
        loginViewModel = new ViewModelProvider(
                this,
                new LoginViewModelFactory(this.getApplication())
        ).get(LoginViewModel.class);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login);
        actionRegister = findViewById(R.id.action_register);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                loginViewModel.loginDataChanged(username.getText().toString(),
                        password.getText().toString());
                String checkUsername = loginViewModel.getErrorMsg(loginViewModel.USERNAME);
                String checkPassword = loginViewModel.getErrorMsg(loginViewModel.PASSWORD);
                if (checkUsername != null) {
                    username.setError(checkUsername);
                    loginBtn.setEnabled(false);
                    return;
                }
                if (checkPassword != null) {
                    password.setError(checkPassword);
                    loginBtn.setEnabled(false);
                    return;
                }
                loginBtn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        username.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);


        loginBtn.setOnClickListener(v -> {
            loginBtn.setEnabled(false);
            loginBtn.setText(R.string.loading);
            // Request login to server
            loginViewModel.login(
                    new OnLogin() {
                        @Override
                        public void success(Users user) {
                            // if true, continue to main activity
                            Intent intent = (user.getAlamatId() == 0) ?
                                    new Intent(
                                            LoginActivity.this,
                                            AlamatActivity.class) :
                                    new Intent(
                                            LoginActivity.this,
                                            MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void error(String message) {
                            loginBtn.setEnabled(true);
                            loginBtn.setText(R.string.action_sign_in);
                            AlertDialog.Builder dialog = new AlertDialog.Builder(LoginActivity.this);
                            dialog.setTitle(getString(R.string.invalid_login));
                            dialog.setPositiveButton("Ok", (dialog1, which) -> {

                            });
                            dialog.show();
                        }
                    },
                    username.getText().toString().trim().toLowerCase(),
                    password.getText().toString().trim()
            );
        });
        actionRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });
    }
}