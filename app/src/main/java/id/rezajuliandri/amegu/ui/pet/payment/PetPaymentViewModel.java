package id.rezajuliandri.amegu.ui.pet.payment;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;

public class PetPaymentViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public PetPaymentViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }
}
