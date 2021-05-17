package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;

@Dao
public interface ProvinsiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<ProvinsiEntity> provinsi);

    @Query("DELETE FROM ProvinsiEntity")
    void delete();

    @Query("SELECT * FROM  ProvinsiEntity")
    LiveData<List<ProvinsiEntity>> getProvinsi();
}
