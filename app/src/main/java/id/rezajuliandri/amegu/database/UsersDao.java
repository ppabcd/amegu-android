package id.rezajuliandri.amegu.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import id.rezajuliandri.amegu.entity.UserAndAlamat;
import id.rezajuliandri.amegu.entity.Users;

@Dao
public interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Users users);

    @Query("DELETE FROM users")
    void delete();

    @Query("SELECT * FROM  users LIMIT 1")
    Users getUser();

//    @Transaction
//    @Query("SELECT * FROM alamat")
//    List<UserAndAlamat> getUsersAndAlamat();
}
