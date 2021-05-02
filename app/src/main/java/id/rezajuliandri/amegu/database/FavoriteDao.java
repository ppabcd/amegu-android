package id.rezajuliandri.amegu.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.entity.Favorite;

@Dao
public interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Favorite favorite);

    @Query("DELETE FROM favorite")
    void deleteAll();

    @Query("DELETE FROM favorite WHERE pet_id = :petId")
    void delete(long petId);

    @Query("SELECT * FROM  favorite WHERE pet_id = :petId LIMIT 1")
    Favorite getFavorite(long petId);
}
