package id.rezajuliandri.amegu.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
<<<<<<< HEAD
=======
import androidx.lifecycle.ViewModelProvider;
>>>>>>> v2
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.Objects;

import id.rezajuliandri.amegu.R;
<<<<<<< HEAD
import id.rezajuliandri.amegu.databinding.ActivityMainBinding;
import id.rezajuliandri.amegu.middleware.BaseActivity;

public class MainActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;
=======
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.databinding.ActivityMainBinding;
import id.rezajuliandri.amegu.utils.BaseActivity;
import id.rezajuliandri.amegu.utils.NetworkUtils;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;
import id.rezajuliandri.amegu.vo.Resource;

public class MainActivity extends BaseActivity {
    ActivityMainBinding activityMainBinding;
    MainViewModel viewModel;
>>>>>>> v2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
<<<<<<< HEAD
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
=======
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        setupActionBar();
        setupViewModel();
        setupViews();
//        refreshUserData();
    }

    private void setupViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(this);
        viewModel = new ViewModelProvider(
                this,
                viewModelFactory
        ).get(MainViewModel.class);
    }

    private void refreshUserData() {
        if (NetworkUtils.isConnectedFast(this)) {
            viewModel.getUser().observe(this, userEntity -> {
                if(userEntity != null){
                    viewModel.getProfile(userEntity.getToken()).observe(this, this::checkProfile);
                }
            });
        }
    }


    private void checkProfile(Resource<UserEntity> userEntityResource) {
        if (userEntityResource != null) {
            switch (userEntityResource.status) {
                case LOADING:
                    break;
                case SUCCESS:
                    break;
                case ERROR:
                    break;

            }
        }
    }

>>>>>>> v2
    private void setupViews() {
        NavController navController = Navigation.findNavController(this, R.id.container);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.navigation_upload, R.id.navigation_account
        ).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(activityMainBinding.bottomNavigation, navController);
    }
<<<<<<< HEAD
=======

    private void setupActionBar() {
        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Melakukan disable pada night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }
>>>>>>> v2
}