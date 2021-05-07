package id.rezajuliandri.amegu.data.database;

import android.app.Application;

import id.rezajuliandri.amegu.data.entity.pet.Favorite;
import id.rezajuliandri.amegu.interfaces.pet.OnAllFavorites;
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

    public void getAllFavorite(OnAllFavorites onAllFavorites){
        AmeguDatabase.databaseWriteExecutor.execute(()->
                onAllFavorites.success(favoriteDao.getAllFavorite())
        );
    }

    public void insert(Favorite favorite) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> favoriteDao.insert(
                favorite.getPetId(),
                favorite.getCreatedAt(),
                favorite.getUpdatedAt()
        ));
    }

    public void deleteAll() {
        AmeguDatabase.databaseWriteExecutor.execute(favoriteDao::deleteAll);
    }

    public void delete(Favorite favorite) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> favoriteDao.delete(favorite.getPetId()));
    }

}
