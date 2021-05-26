package id.rezajuliandri.amegu.ui.pet.adoption;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;

public class PetAdoptionViewModel extends ViewModel {
    AmeguRepository ameguRepository;
    public PetAdoptionViewModel(AmeguRepository ameguRepository){
        this.ameguRepository = ameguRepository;
    }
}
