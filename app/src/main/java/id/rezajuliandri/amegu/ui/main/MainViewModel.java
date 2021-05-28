package id.rezajuliandri.amegu.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class MainViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public MainViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<Resource<UserEntity>> getProfile(String token) {
        return ameguRepository.userRepository().updateProfile(token);
    }
}
