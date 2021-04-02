package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.database.UsersRepository;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;


public class SplashViewModel extends ViewModel {
    private UsersRepository usersRepository;
    public SplashViewModel(Application application) {
        usersRepository = new UsersRepository(application);
    }
    public void checkUserData(OnProfile onProfile){
        usersRepository.getUserData(onProfile);
    }
}
