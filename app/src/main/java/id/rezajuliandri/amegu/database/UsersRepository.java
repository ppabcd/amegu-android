package id.rezajuliandri.amegu.database;

import android.app.Application;

import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

import id.rezajuliandri.amegu.entity.UserAndAlamat;
import id.rezajuliandri.amegu.entity.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;

public class UsersRepository {
    private final UsersDao mUsersDao;

    public UsersRepository(Application application) {
        AmeguDatabase ameguDatabase = AmeguDatabase.getDatabase(application);
        mUsersDao = ameguDatabase.usersDao();
    }

    public void getUserData(OnProfile onProfile) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> onProfile.success(mUsersDao.getUser()));
    }

    public void insert(Users users) {
        users.setId(1);
        AmeguDatabase.databaseWriteExecutor.execute(() -> mUsersDao.insert(users));
    }

    public void delete() {
        AmeguDatabase.databaseWriteExecutor.execute(mUsersDao::delete);
    }
}
