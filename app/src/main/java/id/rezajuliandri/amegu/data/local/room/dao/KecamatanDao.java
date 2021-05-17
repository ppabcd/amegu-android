package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;

@Dao
public interface KecamatanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<KecamatanEntity> kecamatanEntities);

    @Query("DELETE FROM KecamatanEntity")
    void delete();

    @Query("SELECT * FROM  KecamatanEntity WHERE kotaId=:kotaId")
    LiveData<List<KecamatanEntity>> getKecamatan(long kotaId);
}
