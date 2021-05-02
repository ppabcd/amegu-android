package id.rezajuliandri.amegu.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.rezajuliandri.amegu.viewmodel.HomeViewModel;

public class HomeViewmodelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;

    public HomeViewmodelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new HomeViewModel(mApplication);
    }
}