package id.rezajuliandri.amegu.data.database;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import id.rezajuliandri.amegu.data.entity.pet.Favorite;

@Dao
public interface FavoriteDao {
    //    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insert(Favorite favorite);
    @Query("INSERT OR IGNORE INTO favorite VALUES(null, :petId,:createdAt,:updatedAt)")
    void insert(long petId, long createdAt, long updatedAt);

    @Query("DELETE FROM favorite")
    void deleteAll();

    @Query("DELETE FROM favorite WHERE pet_id = :petId")
    void delete(long petId);

    @Query("SELECT * FROM  favorite WHERE pet_id = :petId LIMIT 1")
    Favorite getFavorite(long petId);

    @Query("SELECT * FROM favorite")
    List<Favorite> getAllFavorite();
}
