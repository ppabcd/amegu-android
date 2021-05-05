package id.rezajuliandri.amegu.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.data.entity.auth.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;
import id.rezajuliandri.amegu.ui.address.AddressActivity;
import id.rezajuliandri.amegu.ui.login.LoginActivity;
import id.rezajuliandri.amegu.ui.main.MainActivity;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

public class SplashActivity extends AppCompatActivity {

    SplashViewModel splashViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_spash);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this.getApplication());
        splashViewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(SplashViewModel.class);

        // Proses pengecekan data user apakah tersedia di database atau tidak
        splashViewModel.checkUserData(new OnProfile() {
            @Override
            public void success(Users users) {
                if (Objects.isNull(users)) {
                    goLogin();
                    return;
                }
                if (users.getAlamatId() == 0) {
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

    /**
     * Mengarahkan ke halaman login jika data user tidak tersedia pada database
     */
    private void goLogin() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Mengarahkan ke halaman main jika data user tersedia pada database
     */
    private void goMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Meminta alamat user jika user belum memasukkan alamat
     */
    private void goAddress() {
        Intent intent = new Intent(SplashActivity.this, AddressActivity.class);
        startActivity(intent);
        finish();
    }
}