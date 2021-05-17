package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;

@Dao
public interface KotaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<KotaEntity> kotaEntities);

    @Query("DELETE FROM KotaEntity")
    void delete();

    @Query("SELECT * FROM  KotaEntity WHERE provinsiId=:provinsiId")
    LiveData<List<KotaEntity>> getKota(long provinsiId);
}
