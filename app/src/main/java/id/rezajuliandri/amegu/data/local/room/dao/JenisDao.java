package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;

@Dao
public interface JenisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<JenisEntity> jenisEntity);

    @Query("DELETE FROM JenisEntity")
    void delete();

    @Query("SELECT * FROM  JenisEntity WHERE _id = :id")
    LiveData<JenisEntity> getJenisEntity(long id);

    @Query("SELECT * FROM JenisEntity")
    LiveData<List<JenisEntity>> getAllJenisEntity();
}
