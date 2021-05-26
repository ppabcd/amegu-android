package id.rezajuliandri.amegu.ui.user.adoption;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;

public class UserPetAdoptionViewModel  extends ViewModel {
    AmeguRepository ameguRepository;
    public UserPetAdoptionViewModel(AmeguRepository ameguRepository){
        this.ameguRepository = ameguRepository;
    }
}
