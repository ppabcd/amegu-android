package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;

@Dao
public interface RasDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ArrayList<RasEntity> rasEntity);

    @Query("DELETE FROM RasEntity")
    void delete();

    @Query("SELECT * FROM  RasEntity WHERE _id = :id")
    LiveData<RasEntity> getRasEntity(long id);

    @Query("SELECT * FROM RasEntity")
    LiveData<List<RasEntity>> getAllRasEntity();

    @Query("SELECT * FROM RasEntity WHERE jenisId = :id")
    LiveData<List<RasEntity>> getRasByJenis(long id);
}
