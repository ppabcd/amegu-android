package id.rezajuliandri.amegu.data.repository.user;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import id.rezajuliandri.amegu.data.NetworkBoundResource;
import id.rezajuliandri.amegu.data.local.LocalDataSource;
import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.user.BankAccountEntity;
import id.rezajuliandri.amegu.data.local.entity.user.BankEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.data.remote.ApiResponse;
import id.rezajuliandri.amegu.data.remote.RemoteDataSource;
import id.rezajuliandri.amegu.data.remote.response.auth.login.UserLoginResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.BankAccountResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.BankResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.UserProfileResponse;
import id.rezajuliandri.amegu.utils.AppExecutors;
import id.rezajuliandri.amegu.vo.Resource;

/**
 * Bagian yang mengurus data user baik itu pengambilan data secara online maupun offline pada local
 * database.
 * Jika hanya diambil secara online maka return akan langsung ke remoteDataSource.
 * Namun jika diambil hanya dari database melakukan return data LiveData namun untuk pengambilannya
 * dengan menggunakan appExecutors.diskIO().execute(() -> {});
 * <p>
 * Jika data yang diambil berasal dari internet dan juga lokal dimana jika data tidak tersedia pada
 * lokal maka akan memanggil api dan menyimpannya di lokal namun jika data sudah tersedia langsung
 * melakukan pengambilan data dari local tanpa perlu memanggil api. Untuk hal ini mereturn object
 * dari NetworkBoundResource dan mengembalikan Resource<entity>.
 * Berikut adalah penjelasan beberapa method override yang ada pada NetworkBoundResource.
 * - loadFromDB
 * -+ Method ini digunakan untuk mengambil data dari database sehingga ketika sudah berhasil
 * -+ mengambil data dari API maka bagian ini yang akan dipanggil. Selain itu bagian ini juga akan
 * -+ dipanggil ketika ingin mengetahui apakah sudah ada data tersebut di database atau belum.
 * - shoudFetch
 * -+ Method ini digunakan sebagai indikator apakah memerlukan pengambilan data ke api atau tidak.
 * -+ method ini akan memanggil loadFromDB dan melakukan pemeriksaan kondisi yg ada di dalamnya.
 * -+ jika bernilai true maka akan mengambil data dari api namun jika false maka akan mereturn data
 * -+ dari loadFromDB.
 * - createCall
 * -+ Method ini digunakan untuk melakukan pengambilan data dari api. Bagian ini akan terpanggil
 * -+ jika shouldFetch bernilai true. Hasil dari method ini akan dikirimkan ke method saveCallResult
 * -+ untuk disimpan kedalam database lokal.
 * - saveCallResult
 * -+ Method ini digunakan untuk melakukan konversi response dari api ke entity lokal. Sehingga yang
 * -+ diambil adalah data yang dibutuhkan saja serta melakukan penyesuaian pada data agar sesuai
 * -+ dengan struktur database lokal. Setelah melakukan konversi langkah selanjutnya adalah mengirimkan
 * -+ kedalam database local melalui localDataSource. Setelah selesai maka akan mengembalikan kembali
 * -+ nilai dari dalam database untuk digunakan di viewModel.
 * <p>
 * Informasi lebih lanjut mengenai tiap method ada pada UserDataSource.java
 */
public class UserRepository implements UserDataSource {
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
                localDataSource.deleteUser();
                localDataSource.insertUser(userEntity);
                if (data.getAlamatId() != 0) {
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
                    localDataSource.insertAlamat(alamatEntity);
                }
                if (data.getBankAccount() != null) {
                    BankAccountResponse bankAccountResponse = data.getBankAccount();
                    BankAccountEntity bankAccountEntity = new BankAccountEntity(
                            bankAccountResponse.getId(),
                            bankAccountResponse.getBankId(),
                            bankAccountResponse.getBankAccount(),
                            bankAccountResponse.getOwnerName(),
                            bankAccountResponse.getCreatedAt(),
                            bankAccountResponse.getUpdatedAt()
                    );
                    localDataSource.insertBankAccount(bankAccountEntity);
                    if (bankAccountResponse.getBank() != null) {
                        BankResponse bankResponse = bankAccountResponse.getBank();
                        BankEntity bankEntity = new BankEntity(
                                bankResponse.getId(),
                                bankResponse.getCode(),
                                bankResponse.getName(),
                                bankResponse.getCreatedAt(),
                                bankResponse.getUpdatedAt()
                        );
                        localDataSource.insertBank(bankEntity);
                    }
                }
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
                if (data.getBankAccount() != null) {
                    BankAccountResponse bankAccountResponse = data.getBankAccount();
                    BankAccountEntity bankAccountEntity = new BankAccountEntity(
                            bankAccountResponse.getId(),
                            bankAccountResponse.getBankId(),
                            bankAccountResponse.getBankAccount(),
                            bankAccountResponse.getOwnerName(),
                            bankAccountResponse.getCreatedAt(),
                            bankAccountResponse.getUpdatedAt()
                    );
                    localDataSource.insertBankAccount(bankAccountEntity);
                    if (bankAccountResponse.getBank() != null) {
                        BankResponse bankResponse = bankAccountResponse.getBank();
                        BankEntity bankEntity = new BankEntity(
                                bankResponse.getId(),
                                bankResponse.getCode(),
                                bankResponse.getName(),
                                bankResponse.getCreatedAt(),
                                bankResponse.getUpdatedAt()
                        );
                        localDataSource.insertBank(bankEntity);
                    }
                }
                localDataSource.deleteUser();
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
