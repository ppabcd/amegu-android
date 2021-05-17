package id.rezajuliandri.amegu.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class HomeViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public HomeViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<Resource<List<PetEntity>>> getPets() {
        return ameguRepository.petRepository().getPets();
    }
}
