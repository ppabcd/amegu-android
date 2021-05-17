package id.rezajuliandri.amegu.ui.splash;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import id.rezajuliandri.amegu.databinding.ActivitySplashBinding;
import id.rezajuliandri.amegu.ui.auth.address.AddressActivity;
import id.rezajuliandri.amegu.ui.auth.login.LoginActivity;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class SplashActivity extends AppCompatActivity {
    ActivitySplashBinding binding;
    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupViewModel();
        setupActionBar();
    }

    private void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Melakukan disable pada night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this);
        splashViewModel = new ViewModelProvider(this, viewModelFactory)
                .get(SplashViewModel.class);
        splashViewModel.checkUserLogin().observe(this, userEntity -> {
            if (userEntity == null || userEntity.getEmail() == null) {
                goLogin();
                return;
            }
            if (userEntity.getAlamatId() == 0) {
                goAddress();
                return;
            }
            goMain();
        });
    }

    private void goMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void goLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goAddress() {
        Intent intent = new Intent(this, AddressActivity.class);
        startActivity(intent);
        finish();
    }
}