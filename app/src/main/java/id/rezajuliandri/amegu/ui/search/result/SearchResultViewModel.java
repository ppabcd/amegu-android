package id.rezajuliandri.amegu.ui.search.result;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class SearchResultViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public SearchResultViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public void searchData(String keyword) {
        this.ameguRepository.petRepository().postSearchData(keyword);
    }

    public LiveData<Resource<List<PetEntity>>> searchPet(String keyword) {
        return this.ameguRepository.petRepository().searchPets(keyword);
    }
}
