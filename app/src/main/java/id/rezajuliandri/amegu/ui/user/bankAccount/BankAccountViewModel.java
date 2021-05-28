package id.rezajuliandri.amegu.ui.user.bankAccount;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class BankAccountViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public BankAccountViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<Resource<UserEntity>> refreshData(String token) {
        return ameguRepository.userRepository().updateProfile(token);
    }
}