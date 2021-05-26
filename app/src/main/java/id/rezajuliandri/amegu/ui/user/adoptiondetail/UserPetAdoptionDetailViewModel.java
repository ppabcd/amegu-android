package id.rezajuliandri.amegu.ui.user.adoptiondetail;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;

public class UserPetAdoptionDetailViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public UserPetAdoptionDetailViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }
}
