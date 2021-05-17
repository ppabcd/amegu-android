package id.rezajuliandri.amegu.ui.user.account.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class AccountDetailViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public AccountDetailViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return this.ameguRepository.userRepository().getUser();
    }

    public LiveData<String> updateProfile(String firstName, String lastName, String phoneNumber, String token) {
        return this.ameguRepository.userRepository().updateProfile(firstName, lastName, phoneNumber, token);
    }

    public LiveData<String> updateProfilePassword(String firstName, String lastName, String phoneNumber, String password, String token) {
        return this.ameguRepository.userRepository().updateProfilePassword(firstName, lastName, phoneNumber, password, token);
    }

    public LiveData<Resource<UserEntity>> getProfile(String token) {
        return this.ameguRepository.userRepository().updateProfile(token);
    }
}
