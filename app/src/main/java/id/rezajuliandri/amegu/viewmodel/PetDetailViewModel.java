package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.database.FavoriteRepository;
import id.rezajuliandri.amegu.entity.Favorite;
import id.rezajuliandri.amegu.interfaces.pet.OnFavorite;

public class PetDetailViewModel  extends ViewModel {
    private final Application mApplication;
    private final FavoriteRepository favoriteRepository;
    public PetDetailViewModel(Application application){
        mApplication = application;
        favoriteRepository = new FavoriteRepository(application);
    }

    public void getFavorite(OnFavorite onFavorite){

    }
    public void insert(Favorite favorite){
        favoriteRepository.insert(favorite);
    }
    public void delete(Favorite favorite){
        favoriteRepository.delete(favorite);
    }
    public void deleteAll(){
        favoriteRepository.deleteAll();
    }
}
