package id.rezajuliandri.amegu.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import id.rezajuliandri.amegu.entity.SearchHistory;
import id.rezajuliandri.amegu.entity.Users;

@Database(entities = {SearchHistory.class, Users.class}, version = 1, exportSchema = false)
public abstract class AmeguDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static volatile AmeguDatabase INSTANCE;

    public static AmeguDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AmeguDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AmeguDatabase.class, "amegu")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract SearchHistoryDao searchHistoryDao();
    public abstract UsersDao usersDao();
}
