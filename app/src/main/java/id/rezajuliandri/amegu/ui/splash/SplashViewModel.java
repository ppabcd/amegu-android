package id.rezajuliandri.amegu.ui.splash;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

public class SplashViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public SplashViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> checkUserLogin() {
        return ameguRepository.userRepository().getUser();
    }
}
