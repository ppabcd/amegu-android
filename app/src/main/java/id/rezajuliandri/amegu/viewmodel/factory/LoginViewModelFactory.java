package id.rezajuliandri.amegu.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.rezajuliandri.amegu.viewmodel.LoginViewModel;

public class LoginViewModelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;

    public LoginViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new LoginViewModel(mApplication);
    }
}
