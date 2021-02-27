package id.rezajuliandri.amegu.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.preference.UserPreference;
import id.rezajuliandri.amegu.ui.login.LoginActivity;
import id.rezajuliandri.amegu.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_spash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        UserPreference userPreference = new UserPreference(getApplicationContext());
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if ("".equals(userPreference.getToken())) {
                goLogin();
            }
        }, 3000);
    }

    private void goLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void goMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}