package id.rezajuliandri.amegu.ui.pet.update;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class PetUpdateViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public PetUpdateViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<Resource<PetEntity>> getPetDetail(long petId) {
        return ameguRepository.petRepository().getPetDetail(petId);
    }
}
