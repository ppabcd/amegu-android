package id.rezajuliandri.amegu.database;

import android.app.Application;

import id.rezajuliandri.amegu.entity.Favorite;
import id.rezajuliandri.amegu.interfaces.pet.OnFavorite;

public class FavoriteRepository {
    private final FavoriteDao favoriteDao;

    public FavoriteRepository(Application application) {
        AmeguDatabase ameguDatabase = AmeguDatabase.getDatabase(application);
        favoriteDao = ameguDatabase.favoriteDao();
    }

    public void getFavorite(OnFavorite onFavorite, Favorite favorite) {
        AmeguDatabase.databaseWriteExecutor.execute(() ->
                onFavorite.success(favoriteDao.getFavorite(favorite.getPetId()))
        );
    }

    public void insert(Favorite favorite) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> favoriteDao.insert(favorite));
    }

    public void deleteAll() {
        AmeguDatabase.databaseWriteExecutor.execute(favoriteDao::deleteAll);
    }
    public void delete(Favorite favorite){
        AmeguDatabase.databaseWriteExecutor.execute(()->favoriteDao.delete(favorite.getPetId()));
    }

}
