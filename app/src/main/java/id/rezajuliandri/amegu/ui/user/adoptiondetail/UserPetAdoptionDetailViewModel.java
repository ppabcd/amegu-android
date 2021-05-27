package id.rezajuliandri.amegu.ui.user.adoptiondetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.AdopsiEntity;
import id.rezajuliandri.amegu.data.local.entity.user.InvoiceEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class UserPetAdoptionDetailViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public UserPetAdoptionDetailViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return this.ameguRepository.userRepository().getUser();
    }
}
