package id.rezajuliandri.amegu.ui.user.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;

public class FavoriteViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public FavoriteViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<List<PetEntity>> getPetFavorites() {
        return this.ameguRepository.petRepository().getFavoritePets();
    }
}
