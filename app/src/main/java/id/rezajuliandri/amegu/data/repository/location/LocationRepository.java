package id.rezajuliandri.amegu.data.repository.location;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.NetworkBoundResource;
import id.rezajuliandri.amegu.data.local.LocalDataSource;
import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.data.remote.ApiResponse;
import id.rezajuliandri.amegu.data.remote.RemoteDataSource;
import id.rezajuliandri.amegu.data.remote.response.location.alamat.AlamatResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kecamatan.KecamatanResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kelurahan.KelurahanResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kota.KotaResponse;
import id.rezajuliandri.amegu.data.remote.response.location.provinsi.ProvinsiResponse;
import id.rezajuliandri.amegu.utils.AppExecutors;
import id.rezajuliandri.amegu.vo.Resource;

/**
 * Bagian yang mengurus data lokasi baik itu pengambilan data secara online maupun offline pada local
 * database.
 * Jika hanya diambil secara online maka return akan langsung ke remoteDataSource.
 * Namun jika diambil hanya dari database melakukan return data LiveData namun untuk pengambilannya
 * dengan menggunakan appExecutors.diskIO().execute(() -> {});
 *
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
 *
 * Informasi lebih lanjut mengenai tiap method ada pada LocationDataSource.java
 */
public class LocationRepository implements LocationDataSource {
    private volatile static LocationRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    private LocationRepository(@NonNull RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;

    }

    public static LocationRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (AmeguRepository.class) {
                INSTANCE = new LocationRepository(remoteDataSource, localDataSource, appExecutors);
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<ProvinsiEntity>>> getAllProvince() {
        return new NetworkBoundResource<List<ProvinsiEntity>, List<ProvinsiResponse>>(appExecutors) {
            @Override
            protected LiveData<List<ProvinsiEntity>> loadFromDB() {
                return localDataSource.getAllProvinsi();
            }

            @Override
            protected Boolean shouldFetch(List<ProvinsiEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<ProvinsiResponse>>> createCall() {
                return remoteDataSource.getProvinsi();
            }

            @Override
            protected void saveCallResult(List<ProvinsiResponse> provinsiResponses) {
                ArrayList<ProvinsiEntity> provinsiEntities = new ArrayList<>();
                for (ProvinsiResponse response : provinsiResponses) {
                    ProvinsiEntity provinsiEntity = new ProvinsiEntity(
                            response.getId(),
                            response.getNama()
                    );
                    provinsiEntities.add(provinsiEntity);
                }
                localDataSource.insertProvinsi(provinsiEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<KotaEntity>>> getAllKotaByProvinsi(long provinsiId) {
        return new NetworkBoundResource<List<KotaEntity>, List<KotaResponse>>(appExecutors) {
            @Override
            protected LiveData<List<KotaEntity>> loadFromDB() {
                return localDataSource.getKota(provinsiId);
            }

            @Override
            protected Boolean shouldFetch(List<KotaEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<KotaResponse>>> createCall() {
                return remoteDataSource.getKota(provinsiId);
            }

            @Override
            protected void saveCallResult(List<KotaResponse> kotaResponses) {
                ArrayList<KotaEntity> kotaEntities = new ArrayList<>();
                for (KotaResponse response : kotaResponses) {
                    KotaEntity kotaEntity = new KotaEntity(
                            response.getId(),
                            response.getNama(),
                            response.getProvinsiId()
                    );
                    kotaEntities.add(kotaEntity);
                }
                localDataSource.insertKota(kotaEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<KecamatanEntity>>> getAllKecamatanByKota(long kotaId) {
        return new NetworkBoundResource<List<KecamatanEntity>, List<KecamatanResponse>>(appExecutors) {
            @Override
            protected LiveData<List<KecamatanEntity>> loadFromDB() {
                return localDataSource.getKecamatan(kotaId);
            }

            @Override
            protected Boolean shouldFetch(List<KecamatanEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<KecamatanResponse>>> createCall() {
                return remoteDataSource.getKecamatan(kotaId);
            }

            @Override
            protected void saveCallResult(List<KecamatanResponse> kecamatanResponses) {
                ArrayList<KecamatanEntity> kecamatanEntities = new ArrayList<>();
                for (KecamatanResponse response : kecamatanResponses) {
                    KecamatanEntity kecamatanEntity = new KecamatanEntity(
                            response.getId(),
                            response.getNama(),
                            response.getKotaId()
                    );
                    kecamatanEntities.add(kecamatanEntity);
                }
                localDataSource.insertKecamatan(kecamatanEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<KelurahanEntity>>> getAllKelurahanByKecamatan(long kecamatanId) {
        return new NetworkBoundResource<List<KelurahanEntity>, List<KelurahanResponse>>(appExecutors) {
            @Override
            protected LiveData<List<KelurahanEntity>> loadFromDB() {
                return localDataSource.getKelurahan(kecamatanId);
            }

            @Override
            protected Boolean shouldFetch(List<KelurahanEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<KelurahanResponse>>> createCall() {
                return remoteDataSource.getKelurahan(kecamatanId);
            }

            @Override
            protected void saveCallResult(List<KelurahanResponse> kelurahanResponses) {
                ArrayList<KelurahanEntity> kelurahanEntities = new ArrayList<>();
                for (KelurahanResponse response : kelurahanResponses) {
                    KelurahanEntity kelurahanEntity = new KelurahanEntity(
                            response.getId(),
                            response.getNama(),
                            response.getKecamatanId()
                    );
                    kelurahanEntities.add(kelurahanEntity);
                }
                localDataSource.insertKelurahan(kelurahanEntities);
            }
        }.asLiveData();
    }

    public LiveData<Resource<AlamatEntity>> getAlamat(int alamatId, String token) {
        return new NetworkBoundResource<AlamatEntity, AlamatResponse>(appExecutors) {
            @Override
            protected LiveData<AlamatEntity> loadFromDB() {
                return localDataSource.getAlamat(alamatId);
            }

            @Override
            protected Boolean shouldFetch(AlamatEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<AlamatResponse>> createCall() {
                return remoteDataSource.getAlamat(token);
            }

            @Override
            protected void saveCallResult(AlamatResponse response) {
                AlamatEntity alamatEntity = new AlamatEntity(
                        response.getId(),
                        response.getAlamat(),
                        response.getKelurahanName(),
                        response.getKelurahanId(),
                        response.getKecamatanName(),
                        response.getKecamatanId(),
                        response.getKotaName(),
                        response.getKotaId(),
                        response.getProvinsiName(),
                        response.getProvinsiId()
                );
                localDataSource.insertAlamat(alamatEntity);
            }
        }.asLiveData();
    }

    public LiveData<String> postAlamat(String alamat, long provinsiId, long kotaId, long kecamatanId, long kelurahanId, String token) {
        return remoteDataSource.sendAlamat(alamat, provinsiId, kotaId, kecamatanId, kelurahanId, token);
    }
}
