package id.rezajuliandri.amegu.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.entity.Alamat;
import id.rezajuliandri.amegu.entity.Users;

@Dao
public interface AlamatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Alamat alamat);

    @Query("DELETE FROM alamat")
    void delete();

    @Query("SELECT * FROM  alamat LIMIT 1")
    Alamat getAlamat();
}
