package id.rezajuliandri.amegu.viewmodel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.rezajuliandri.amegu.viewmodel.AlamatViewModel;
import id.rezajuliandri.amegu.viewmodel.LoginViewModel;
import id.rezajuliandri.amegu.viewmodel.PetDetailViewModel;

public class PetDetailViewModelFactory implements ViewModelProvider.Factory{
    private final Application mApplication;

    public PetDetailViewModelFactory(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PetDetailViewModel(mApplication);
    }
}
