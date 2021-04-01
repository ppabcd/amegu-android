package id.rezajuliandri.amegu.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import id.rezajuliandri.amegu.entity.Users;

@Dao
public interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Users users);

    @Query("DELETE FROM users")
    void delete();

    @Query("SELECT * FROM  users LIMIT 1")
    Users getUser();
}
