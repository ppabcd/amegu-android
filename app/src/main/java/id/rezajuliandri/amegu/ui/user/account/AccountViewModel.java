package id.rezajuliandri.amegu.ui.user.account;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

public class AccountViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public AccountViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<AlamatEntity> getAlamat(int alamatId) {
        return ameguRepository.userRepository().getAlamat(alamatId);
    }

    public LiveData<String> logout() {
        return ameguRepository.userRepository().logout();
    }
}
