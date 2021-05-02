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

import id.rezajuliandri.amegu.entity.Alamat;
import id.rezajuliandri.amegu.entity.Favorite;
import id.rezajuliandri.amegu.entity.SearchHistory;
import id.rezajuliandri.amegu.entity.Users;

/**
 * Bagian yang mengatur koneksi kedalam database
 */
@Database(entities = {SearchHistory.class, Users.class, Alamat.class, Favorite.class}, version = 1, exportSchema = false)
public abstract class AmeguDatabase extends RoomDatabase {
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static volatile AmeguDatabase INSTANCE;

    /**
     * Mengambil data object database dari room
     * @param context Context dari activity yang digunakan
     * @return AmeguDatabase
     */
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

    /**
     * Table search
     * @return  SearchHistoryDao
     */
    public abstract SearchHistoryDao searchHistoryDao();

    /**
     * Table users
     * @return UsersDao
     */
    public abstract UsersDao usersDao();

    /**
     * Table alamat
     * @return AlamatDao
     */
    public abstract AlamatDao alamatDao();

    /**
     * Table favorite
     * @return FavoriteDao
     */
    public abstract FavoriteDao favoriteDao();
}
