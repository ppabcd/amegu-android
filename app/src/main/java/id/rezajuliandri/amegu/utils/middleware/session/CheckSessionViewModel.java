package id.rezajuliandri.amegu.utils.middleware.session;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

public class CheckSessionViewModel extends ViewModel {

    AmeguRepository ameguRepository;

    public CheckSessionViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<String> checkToken(String token) {
        return ameguRepository.userRepository().checkToken(token);
    }

    public LiveData<String> logout() {
        return ameguRepository.userRepository().logout();
    }
}
