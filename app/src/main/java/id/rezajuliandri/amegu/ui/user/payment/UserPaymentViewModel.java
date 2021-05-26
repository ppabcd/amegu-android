package id.rezajuliandri.amegu.ui.user.payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

public class UserPaymentViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public UserPaymentViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser(){
        return ameguRepository.userRepository().getUser();
    }
}
