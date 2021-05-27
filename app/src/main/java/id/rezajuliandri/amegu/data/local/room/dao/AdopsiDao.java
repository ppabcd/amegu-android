package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.local.entity.user.AdopsiEntity;

@Dao
public interface AdopsiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AdopsiEntity adopsiEntity);

    @Query("DELETE FROM AdopsiEntity")
    void delete();

    @Query("SELECT * FROM  AdopsiEntity WHERE _id = :id")
    LiveData<AdopsiEntity> getAdopsi(long id);

    @Query("SELECT * FROM AdopsiEntity WHERE hewanId = :id ORDER BY _id DESC LIMIT 1")
    LiveData<AdopsiEntity> getAdopsiByPetId(long id);
}
