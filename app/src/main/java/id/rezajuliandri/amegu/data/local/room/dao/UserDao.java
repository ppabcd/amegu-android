package id.rezajuliandri.amegu.data.local.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(UserEntity users);

    @Query("DELETE FROM UserEntity")
    void delete();

    @Query("SELECT * FROM  UserEntity LIMIT 1")
    LiveData<UserEntity> getUser();
}
