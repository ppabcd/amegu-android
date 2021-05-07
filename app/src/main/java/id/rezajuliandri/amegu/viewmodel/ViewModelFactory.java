package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.ui.account_detail.AccountDetailViewModel;
import id.rezajuliandri.amegu.ui.address.AddressViewModel;
import id.rezajuliandri.amegu.ui.home.HomeViewModel;
import id.rezajuliandri.amegu.ui.login.LoginViewModel;
import id.rezajuliandri.amegu.ui.pet_detail.PetDetailViewModel;
import id.rezajuliandri.amegu.ui.register.RegisterViewModel;
import id.rezajuliandri.amegu.ui.search.SearchViewModel;
import id.rezajuliandri.amegu.ui.splash.SplashViewModel;
import id.rezajuliandri.amegu.ui.upload.UploadViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Application mApplication;
    private static volatile ViewModelFactory INSTANCE;

    public ViewModelFactory(Application application) {
        mApplication = application;
    }

    public static ViewModelFactory getInstance(Application application){
        if(INSTANCE == null){
            synchronized (ViewModelFactory.class){
                INSTANCE = new ViewModelFactory(application);
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AddressViewModel.class)) {
            return (T) new AddressViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(PetDetailViewModel.class)) {
            return (T) new PetDetailViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(UploadViewModel.class)) {
            return (T) new UploadViewModel(mApplication);
        } else if(modelClass.isAssignableFrom(AccountDetailViewModel.class)){
            return (T) new AccountDetailViewModel(mApplication);
        } else if(modelClass.isAssignableFrom(Session.class)){
            return (T) new Session(mApplication);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
