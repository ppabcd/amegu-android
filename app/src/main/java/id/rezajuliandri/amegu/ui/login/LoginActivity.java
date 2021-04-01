package id.rezajuliandri.amegu.ui.login;

import android.content.DialogInterface;
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
import id.rezajuliandri.amegu.interfaces.OnLogin;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.viewmodel.LoginViewModel;
import id.rezajuliandri.amegu.viewmodel.LoginViewModelFactory;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button button;
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
        button = findViewById(R.id.login);

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
                    button.setEnabled(false);
                    return;
                }
                if (checkPassword != null) {
                    password.setError(checkPassword);
                    button.setEnabled(false);
                    return;
                }
                button.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        username.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);


        button.setOnClickListener(v -> {
            button.setEnabled(false);
            // Request login to server
            loginViewModel.login(
                    new OnLogin() {
                        @Override
                        public void success(Users user) {
                            // if true, continue to main activity
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);

                            finish();
                        }

                        @Override
                        public void error(String message) {
                            button.setEnabled(false);
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
    }
}