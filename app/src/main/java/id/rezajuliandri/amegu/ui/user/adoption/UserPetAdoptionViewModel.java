package id.rezajuliandri.amegu.ui.user.adoption;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

public class UserPetAdoptionViewModel  extends ViewModel {
    AmeguRepository ameguRepository;
    public UserPetAdoptionViewModel(AmeguRepository ameguRepository){
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return this.ameguRepository.userRepository().getUser();
    }
}
