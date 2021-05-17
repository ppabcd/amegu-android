package id.rezajuliandri.amegu.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.SearchEntity;

public class SearchViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public SearchViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public void deleteAllSearchHistory() {
        ameguRepository.petRepository().removeSearchData();
    }

    public LiveData<List<SearchEntity>> getAllSearchHistory() {
        return ameguRepository.petRepository().getSearchData();
    }
}
