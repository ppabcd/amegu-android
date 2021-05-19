package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.local.entity.user.BankEntity;

@Dao
public interface BankDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BankEntity bankEntity);

    @Query("DELETE FROM BankEntity")
    void delete();

    @Query("SELECT * FROM  BankEntity WHERE _id = :id")
    LiveData<BankEntity> getBank(long id);
}
