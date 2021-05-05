package id.rezajuliandri.amegu.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.entity.location.Alamat;

@Dao
public interface AlamatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Alamat alamat);

    @Query("DELETE FROM alamat")
    void delete();

    @Query("SELECT * FROM  alamat LIMIT 1")
    Alamat getAlamat();
}
