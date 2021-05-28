package id.rezajuliandri.amegu.ui.user.pets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class PetUserViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public PetUserViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<Resource<List<PetEntity>>> getMyPets(String token) {
        return ameguRepository.petRepository().getMyPets(token);
    }

    public LiveData<UserEntity> getUserData() {
        return ameguRepository.userRepository().getUser();
    }
}
