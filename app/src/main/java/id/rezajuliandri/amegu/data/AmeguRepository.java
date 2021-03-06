package id.rezajuliandri.amegu.data;

import android.content.Context;

import androidx.annotation.NonNull;

import id.rezajuliandri.amegu.data.local.LocalDataSource;
import id.rezajuliandri.amegu.data.remote.RemoteDataSource;
import id.rezajuliandri.amegu.data.repository.location.LocationRepository;
import id.rezajuliandri.amegu.data.repository.pet.PetRepository;
import id.rezajuliandri.amegu.data.repository.user.UserRepository;
import id.rezajuliandri.amegu.utils.AppExecutors;

/**
 * Bagian yang menangani pengambilan data dan menghubungkan ke network serta local database.
 * Repository dibagi menjadi beberapa bagian sesaui subjectnya.
 * - LocationRepository yang menghandle semua data mengenai lokasi
 * - UserRepository yang menghandle semua data mengenai user
 * - PetRepository yang menghandle semua data mengenai hewan
 */
public class AmeguRepository implements AmeguDataSource {
    private volatile static AmeguRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;
    private final Context context;

    private AmeguRepository(@NonNull RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors, Context context) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
        this.context = context;
    }

    public static AmeguRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors, Context context) {
        if (INSTANCE == null) {
            synchronized (AmeguRepository.class) {
                INSTANCE = new AmeguRepository(remoteDataSource, localDataSource, appExecutors, context);
            }
        }
        return INSTANCE;
    }

    public LocationRepository locationRepository() {
        return LocationRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }

    public UserRepository userRepository() {
        return UserRepository.getInstance(remoteDataSource, localDataSource, appExecutors);
    }

    public PetRepository petRepository() {
        return PetRepository.getInstance(remoteDataSource, localDataSource, appExecutors, context);
    }
}
