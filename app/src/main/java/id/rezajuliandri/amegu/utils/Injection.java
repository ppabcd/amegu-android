package id.rezajuliandri.amegu.utils;

import android.content.Context;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.LocalDataSource;
import id.rezajuliandri.amegu.data.local.room.AmeguDatabase;
import id.rezajuliandri.amegu.data.remote.RemoteDataSource;

public class Injection {
    public static AmeguRepository provideRepository(Context context) {
        RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
        AmeguDatabase ameguDatabase = AmeguDatabase.getInstance(context);
        LocalDataSource localDataSource = LocalDataSource.getInstance(ameguDatabase);
        AppExecutors appExecutors = new AppExecutors();

        return AmeguRepository.getInstance(remoteDataSource, localDataSource, appExecutors, context);
    }
}

