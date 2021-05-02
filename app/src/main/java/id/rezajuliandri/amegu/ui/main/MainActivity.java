package id.rezajuliandri.amegu.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
import id.rezajuliandri.amegu.databinding.ActivityMainBinding;
import id.rezajuliandri.amegu.ui.middleware.BaseActivity;

public class MainActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Melakukan disable pada night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setupViews();
    }

    /**
     * View yang akan digunakan pada aplikasi
     */
    private void setupViews() {
        NavController navController = Navigation.findNavController(this, R.id.container);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.navigation_upload, R.id.navigation_account
        ).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(activityMainBinding.bottomNavigation, navController);
    }
}