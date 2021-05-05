package id.rezajuliandri.amegu.ui.pet_detail;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.database.FavoriteRepository;
import id.rezajuliandri.amegu.data.entity.pet.Favorite;
import id.rezajuliandri.amegu.interfaces.pet.OnFavorite;

public class PetDetailViewModel extends ViewModel {
    private final Application mApplication;
    private final FavoriteRepository favoriteRepository;

    public PetDetailViewModel(Application application) {
        mApplication = application;
        favoriteRepository = new FavoriteRepository(application);
    }

    public void getFavorite(OnFavorite onFavorite, Favorite favorite) {
        favoriteRepository.getFavorite(onFavorite, favorite);
    }

    public void insertOrDelete(Favorite favorite){
        this.getFavorite(new OnFavorite() {
            @Override
            public void success(Favorite fav) {
                if(fav == null){
                    insert(favorite);
                    return;
                }
                delete(favorite);
            }

            @Override
            public void error(String error) {
                Log.e("Favorite_Error", "Favorite insert error");
            }
        }, favorite);
    }
    public void insert(Favorite favorite) {
        favoriteRepository.insert(favorite);
    }

    public void delete(Favorite favorite) {
        favoriteRepository.delete(favorite);
    }

    public void deleteAll() {
        favoriteRepository.deleteAll();
    }
}
