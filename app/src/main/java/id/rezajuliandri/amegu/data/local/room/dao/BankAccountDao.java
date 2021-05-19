package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.local.entity.user.BankAccountEntity;

@Dao
public interface BankAccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(BankAccountEntity bankAccountEntity);

    @Query("DELETE FROM BankAccountEntity")
    void delete();

    @Query("SELECT * FROM  BankAccountEntity WHERE _id = :id")
    LiveData<BankAccountEntity> getBankAccount(long id);
}
