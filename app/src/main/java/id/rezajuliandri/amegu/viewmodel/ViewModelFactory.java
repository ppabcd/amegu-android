package id.rezajuliandri.amegu.viewmodel;

<<<<<<< HEAD
import android.app.Application;
=======
import android.content.Context;
>>>>>>> v2

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

<<<<<<< HEAD
import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.ui.account_detail.AccountDetailViewModel;
import id.rezajuliandri.amegu.ui.address.AddressViewModel;
import id.rezajuliandri.amegu.ui.favorite.FavoriteViewModel;
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

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(application);
=======
import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.ui.auth.address.AddressViewModel;
import id.rezajuliandri.amegu.ui.auth.login.LoginViewModel;
import id.rezajuliandri.amegu.ui.auth.register.RegisterViewModel;
import id.rezajuliandri.amegu.ui.home.HomeViewModel;
import id.rezajuliandri.amegu.ui.main.MainViewModel;
import id.rezajuliandri.amegu.ui.pet.detail.PetDetailViewModel;
import id.rezajuliandri.amegu.ui.pet.upload.UploadViewModel;
import id.rezajuliandri.amegu.ui.search.SearchViewModel;
import id.rezajuliandri.amegu.ui.search.result.SearchResultViewModel;
import id.rezajuliandri.amegu.ui.splash.SplashViewModel;
import id.rezajuliandri.amegu.ui.user.account.AccountViewModel;
import id.rezajuliandri.amegu.ui.user.account.detail.AccountDetailViewModel;
import id.rezajuliandri.amegu.ui.user.address.AddressUserViewModel;
import id.rezajuliandri.amegu.ui.user.favorite.FavoriteViewModel;
import id.rezajuliandri.amegu.ui.user.pets.PetUserViewModel;
import id.rezajuliandri.amegu.utils.Injection;
import id.rezajuliandri.amegu.utils.middleware.session.CheckSessionViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private static volatile ViewModelFactory INSTANCE;
    private final AmeguRepository ameguRepository;

    public ViewModelFactory(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public static ViewModelFactory getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                INSTANCE = new ViewModelFactory(Injection.provideRepository(context));
>>>>>>> v2
            }
        }
        return INSTANCE;
    }

<<<<<<< HEAD
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
        } else if (modelClass.isAssignableFrom(AccountDetailViewModel.class)) {
            return (T) new AccountDetailViewModel(mApplication);
        } else if (modelClass.isAssignableFrom(Session.class)) {
            return (T) new Session(mApplication);
        } else if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(mApplication);
        }

=======
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(AddressViewModel.class)) {
            return (T) new AddressViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(PetDetailViewModel.class)) {
            return (T) new PetDetailViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
            return (T) new SearchViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(SearchResultViewModel.class)) {
            return (T) new SearchResultViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(CheckSessionViewModel.class)) {
            return (T) new CheckSessionViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            return (T) new AccountViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(AccountDetailViewModel.class)) {
            return (T) new AccountDetailViewModel(ameguRepository);
        } else if (modelClass.isAssignableFrom(FavoriteViewModel.class)) {
            return (T) new FavoriteViewModel(ameguRepository);
        }else if (modelClass.isAssignableFrom(PetUserViewModel.class)) {
            return (T) new PetUserViewModel(ameguRepository);
        } else if(modelClass.isAssignableFrom(AddressUserViewModel.class)){
            return (T) new AddressUserViewModel(ameguRepository);
        } else if(modelClass.isAssignableFrom(MainViewModel.class)){
            return (T) new MainViewModel(ameguRepository);
        } else if(modelClass.isAssignableFrom(UploadViewModel.class)){
            return (T) new UploadViewModel(ameguRepository);
        } else if(modelClass.isAssignableFrom(RegisterViewModel.class)){
            return (T) new RegisterViewModel(ameguRepository);
        }
>>>>>>> v2
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
