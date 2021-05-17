package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;

@Dao
public interface KelurahanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<KelurahanEntity> kelurahanEntities);

    @Query("DELETE FROM KelurahanEntity")
    void delete();

    @Query("SELECT * FROM  KelurahanEntity WHERE kecamatanId=:kecamatanId")
    LiveData<List<KelurahanEntity>> getKelurahan(long kecamatanId);
}
