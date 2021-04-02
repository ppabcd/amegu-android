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
import id.rezajuliandri.amegu.interfaces.auth.OnRegister;
import id.rezajuliandri.amegu.viewmodel.RegisterViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.RegisterViewModelFactory;

public class RegisterActivity extends AppCompatActivity {

    RegisterViewModel registerViewModel;
    EditText username, password, firstName, lastName;
    Button registerBtn, actionLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Objects.requireNonNull(getSupportActionBar()).hide();

        // View Model
        registerViewModel = new ViewModelProvider(
                this,
                new RegisterViewModelFactory(this.getApplication())
        ).get(RegisterViewModel.class);

        // Init
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        registerBtn = findViewById(R.id.register);
        actionLogin = findViewById(R.id.action_login);

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                registerViewModel.registerDataChanged(firstName.getText().toString(),
                        lastName.getText().toString(),
                        username.getText().toString(),
                        password.getText().toString());
                String checkUsername = registerViewModel.getErrorMsg(registerViewModel.USERNAME);
                String checkPassword = registerViewModel.getErrorMsg(registerViewModel.PASSWORD);
                String checkFirstName = registerViewModel.getErrorMsg(registerViewModel.FIRST_NAME);
                String checkLastName = registerViewModel.getErrorMsg(registerViewModel.LAST_NAME);
                registerBtn.setEnabled(false);

                if (checkFirstName != null) {
                    firstName.setError(checkFirstName);
                    return;
                }
                if (checkLastName != null) {
                    lastName.setError(checkLastName);
                    return;
                }
                if (checkUsername != null) {
                    username.setError(checkUsername);
                    return;
                }
                if (checkPassword != null) {
                    password.setError(checkPassword);
                    return;
                }
                registerBtn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        firstName.addTextChangedListener(afterTextChangedListener);
        lastName.addTextChangedListener(afterTextChangedListener);
        username.addTextChangedListener(afterTextChangedListener);
        password.addTextChangedListener(afterTextChangedListener);


        // If button register clicked
        registerBtn.setOnClickListener(v -> {
            registerBtn.setEnabled(false);
            registerBtn.setText(R.string.loading);
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
                            registerBtn.setText(R.string.action_register);
                            registerBtn.setEnabled(true);
                        }
                    },
                    firstName.getText().toString().trim(),
                    lastName.getText().toString().trim(),
                    username.getText().toString().trim().toLowerCase(),
                    password.getText().toString().trim()
            );
        });
        // If button action login clicked
        actionLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}