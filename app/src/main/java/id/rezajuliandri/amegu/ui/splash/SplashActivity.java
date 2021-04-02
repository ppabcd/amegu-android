package id.rezajuliandri.amegu.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.entity.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;
import id.rezajuliandri.amegu.ui.auth.AlamatActivity;
import id.rezajuliandri.amegu.ui.auth.LoginActivity;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.viewmodel.SplashViewModel;
import id.rezajuliandri.amegu.viewmodel.factory.SplashViewModelFactory;

public class SplashActivity extends AppCompatActivity {

    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_spash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        splashViewModel = new ViewModelProvider(
                this,
                new SplashViewModelFactory(this.getApplication())
        ).get(SplashViewModel.class);

        // Check user authentication
        splashViewModel.checkUserData(new OnProfile() {
            @Override
            public void success(Users users) {
                if (Objects.isNull(users)) {
                    goLogin();
                    return;
                }
                if(users.getAlamatId() == 0){
                    goAddress();
                    return;
                }
                goMain();
            }

            @Override
            public void error(String message) {
                Log.e("CheckUser", "Check user failed");
            }
        });
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

    private void goAddress() {
        Intent intent = new Intent(SplashActivity.this, AlamatActivity.class);
        startActivity(intent);
        finish();
    }
}