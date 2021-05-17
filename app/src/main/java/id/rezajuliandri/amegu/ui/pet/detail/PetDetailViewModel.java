package id.rezajuliandri.amegu.ui.pet.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class PetDetailViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public PetDetailViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    LiveData<Resource<PetEntity>> getPetDetail(long petId) {
        return ameguRepository.petRepository().getPetDetail(petId);
    }

    public void updateFavoritePet(int isFavorite, long petId) {
        ameguRepository.petRepository().updateFavorite(isFavorite, petId);
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<String> deleteHewan(long id, String token) {
        return ameguRepository.petRepository().deleteHewan(id, token);
    }
}
