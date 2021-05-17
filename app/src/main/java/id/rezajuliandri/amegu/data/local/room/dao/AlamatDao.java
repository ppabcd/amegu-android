package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;

@Dao
public interface AlamatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AlamatEntity alamat);

    @Query("DELETE FROM AlamatEntity")
    void delete();

    @Query("SELECT * FROM  AlamatEntity WHERE _id = :id")
    LiveData<AlamatEntity> getAlamat(long id);
}
