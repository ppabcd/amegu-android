package id.rezajuliandri.amegu.data.database;

import android.app.Application;

import id.rezajuliandri.amegu.data.entity.auth.Users;
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
