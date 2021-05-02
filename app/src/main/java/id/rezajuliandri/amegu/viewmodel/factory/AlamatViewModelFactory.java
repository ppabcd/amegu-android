package id.rezajuliandri.amegu.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.rezajuliandri.amegu.viewmodel.AlamatViewModel;
import id.rezajuliandri.amegu.viewmodel.LoginViewModel;

public class AlamatViewModelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;
    private final LoginViewModel loginViewModel;

    public AlamatViewModelFactory(Application application, LoginViewModel loginViewModel) {
        mApplication = application;
        this.loginViewModel = loginViewModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AlamatViewModel(mApplication, loginViewModel);
    }
}