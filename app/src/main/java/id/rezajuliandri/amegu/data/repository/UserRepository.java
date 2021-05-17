package id.rezajuliandri.amegu.data.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import id.rezajuliandri.amegu.data.NetworkBoundResource;
import id.rezajuliandri.amegu.data.local.LocalDataSource;
import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.data.remote.ApiResponse;
import id.rezajuliandri.amegu.data.remote.RemoteDataSource;
import id.rezajuliandri.amegu.data.remote.response.auth.login.UserLoginResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.UserProfileResponse;
import id.rezajuliandri.amegu.utils.AppExecutors;
import id.rezajuliandri.amegu.vo.Resource;

public class UserRepository {
    private volatile static UserRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    private UserRepository(@NonNull RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;

    }

    public static UserRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                INSTANCE = new UserRepository(remoteDataSource, localDataSource, appExecutors);
            }
        }
        return INSTANCE;
    }

    public LiveData<UserEntity> getUser() {
        return localDataSource.ameguDatabase.userDao().getUser();
    }

    public LiveData<Resource<UserEntity>> loginUser(String username, String password) {
        return new NetworkBoundResource<UserEntity, UserLoginResponse>(appExecutors) {
            @Override
            protected LiveData<UserEntity> loadFromDB() {
                return localDataSource.getUser();
            }

            @Override
            protected Boolean shouldFetch(UserEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiResponse<UserLoginResponse>> createCall() {
                return remoteDataSource.login(username, password);
            }

            @Override
            protected void saveCallResult(UserLoginResponse loginData) {
                UserEntity userEntity = new UserEntity(1,
                        null,
                        null,
                        null,
                        loginData.getToken(),
                        null,
                        null,
                        0);
                localDataSource.insertUser(userEntity);
            }
        }.asLiveData();
    }

    public LiveData<Resource<UserEntity>> getProfile(String token) {
        return new NetworkBoundResource<UserEntity, UserProfileResponse>(appExecutors) {
            @Override
            protected LiveData<UserEntity> loadFromDB() {
                return localDataSource.getUser();
            }

            @Override
            protected Boolean shouldFetch(UserEntity data) {
                return data.getFirstName() == null || data.getEmail() == null;
            }

            @Override
            protected LiveData<ApiResponse<UserProfileResponse>> createCall() {
                return remoteDataSource.getProfile(token);
            }

            @Override
            protected void saveCallResult(UserProfileResponse data) {
                UserEntity userEntity = new UserEntity(data.getId(),
                        data.getFirstName(),
                        data.getLastName(),
                        data.getEmail(),
                        token,
                        data.getPhoneNumber(),
                        "",
                        data.getAlamatId());
                AlamatEntity alamatEntity = new AlamatEntity(
                        data.getAlamatId(),
                        data.getAlamat().getAlamat(),
                        data.getAlamat().getKelurahanName(),
                        data.getAlamat().getKelurahanId(),
                        data.getAlamat().getKecamatanName(),
                        data.getAlamat().getKecamatanId(),
                        data.getAlamat().getKotaName(),
                        data.getAlamat().getKotaId(),
                        data.getAlamat().getProvinsiName(),
                        data.getAlamat().getProvinsiId()
                );
                localDataSource.deleteUser();
                localDataSource.insertUser(userEntity);
                localDataSource.insertAlamat(alamatEntity);
            }
        }.asLiveData();
    }

    public LiveData<Resource<UserEntity>> updateProfile(String token) {
        return new NetworkBoundResource<UserEntity, UserProfileResponse>(appExecutors) {
            @Override
            protected LiveData<UserEntity> loadFromDB() {
                return localDataSource.getUser();
            }

            @Override
            protected Boolean shouldFetch(UserEntity data) {
                return true;
            }

            @Override
            protected LiveData<ApiResponse<UserProfileResponse>> createCall() {
                return remoteDataSource.getProfile(token);
            }

            @Override
            protected void saveCallResult(UserProfileResponse data) {
                UserEntity userEntity = new UserEntity(1,
                        data.getFirstName(),
                        data.getLastName(),
                        data.getEmail(),
                        token,
                        data.getPhoneNumber(),
                        "",
                        data.getAlamatId());
                AlamatEntity alamatEntity = new AlamatEntity(
                        data.getAlamatId(),
                        data.getAlamat().getAlamat(),
                        data.getAlamat().getKelurahanName(),
                        data.getAlamat().getKelurahanId(),
                        data.getAlamat().getKecamatanName(),
                        data.getAlamat().getKecamatanId(),
                        data.getAlamat().getKotaName(),
                        data.getAlamat().getKotaId(),
                        data.getAlamat().getProvinsiName(),
                        data.getAlamat().getProvinsiId()
                );
                localDataSource.insertUser(userEntity);
                localDataSource.insertAlamat(alamatEntity);
            }
        }.asLiveData();
    }

    public LiveData<String> checkToken(String token) {
        return remoteDataSource.checkToken(token);
    }

    public LiveData<String> logout() {
        MutableLiveData<String> status = new MutableLiveData<>();
        appExecutors.diskIO().execute(() -> {
            localDataSource.ameguDatabase.userDao().delete();
            status.postValue("Ok");
        });
        return status;
    }

    public LiveData<AlamatEntity> getAlamat(int alamatId) {
        return localDataSource.ameguDatabase.alamatDao().getAlamat(alamatId);
    }

    public LiveData<String> updateProfile(String firstName, String lastName, String phoneNumber, String token) {
        return remoteDataSource.updateProfile(firstName, lastName, phoneNumber, token);
    }

    public LiveData<String> updateProfilePassword(String firstName, String lastName, String phoneNumber, String password, String token) {
        return remoteDataSource.updateProfilePassword(firstName, lastName, phoneNumber, password, token);
    }

    public LiveData<String> register(String firstname, String lastName, String email, String password, String phoneNumber) {
        return remoteDataSource.register(firstname, lastName, email, password, phoneNumber);
    }
}
