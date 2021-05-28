package id.rezajuliandri.amegu.ui.user.adoptiondetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

public class UserPetAdoptionDetailViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public UserPetAdoptionDetailViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return this.ameguRepository.userRepository().getUser();
    }
}
