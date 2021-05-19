package id.rezajuliandri.amegu.data.repository.pet;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import id.rezajuliandri.amegu.data.NetworkBoundResource;
import id.rezajuliandri.amegu.data.local.LocalDataSource;
import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.SearchEntity;
import id.rezajuliandri.amegu.data.remote.ApiResponse;
import id.rezajuliandri.amegu.data.remote.RemoteDataSource;
import id.rezajuliandri.amegu.data.remote.response.attachment.upload.AttachmentResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.jenis.JenisResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.pets.PetResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.ras.RasResponse;
import id.rezajuliandri.amegu.data.repository.user.UserRepository;
import id.rezajuliandri.amegu.utils.AppExecutors;
import id.rezajuliandri.amegu.utils.NetworkUtils;
import id.rezajuliandri.amegu.vo.Resource;

/**
 * Bagian yang mengurus data hewan baik itu pengambilan data secara online maupun offline pada local
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
 * Informasi lebih lanjut mengenai tiap method ada pada PetDataSource.java
 */
public class PetRepository implements PetDataSource{
    private volatile static PetRepository INSTANCE = null;

    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;
    private final Context context;

    private PetRepository(@NonNull RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors, Context context) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        this.appExecutors = appExecutors;
        this.context = context;
    }

    public static PetRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource, AppExecutors appExecutors, Context context) {
        if (INSTANCE == null) {
            synchronized (UserRepository.class) {
                INSTANCE = new PetRepository(remoteDataSource, localDataSource, appExecutors, context);
            }
        }
        return INSTANCE;
    }

    public LiveData<Resource<List<PetEntity>>> getPets() {
        return new NetworkBoundResource<List<PetEntity>, List<PetResponse>>(appExecutors) {
            @Override
            protected LiveData<List<PetEntity>> loadFromDB() {
                return localDataSource.getPets();
            }

            @Override
            protected Boolean shouldFetch(List<PetEntity> data) {
                return NetworkUtils.isConnectedFast(context);
            }

            @Override
            protected LiveData<ApiResponse<List<PetResponse>>> createCall() {
                return remoteDataSource.getPets();
            }

            @Override
            protected void saveCallResult(List<PetResponse> petResponses) {
                ArrayList<PetEntity> petEntities = new ArrayList<>();
                ArrayList<AttachmentEntity> attachmentEntities = new ArrayList<>();
                for (PetResponse response : petResponses) {
                    PetEntity petEntity = new PetEntity(
                            response.getId(),
                            response.getUsia(),
                            response.getKondisi(),
                            response.getRasId(),
                            response.getRas().getRas(),
                            response.getUserId(),
                            response.getUser().getFullName(),
                            response.getUser().getAlamat().getProvinsiName(),
                            response.getNamaHewan(),
                            response.getBeratBadan(),
                            response.getHarga(),
                            response.getJenisKelamin(),
                            response.getAttachmentId(),
                            response.getAttachment().getUrl(),
                            response.getDeskripsi(),
                            0,
                            response.getCreatedAt(),
                            response.getUpdatedAt()
                    );
                    if(response.getAttachment() != null){
                        AttachmentResponse attachmentResponse = response.getAttachment();
                        AttachmentEntity attachmentEntity = new AttachmentEntity(
                                attachmentResponse.getId(),
                                attachmentResponse.getUserId(),
                                attachmentResponse.getHewanId(),
                                attachmentResponse.getFilename(),
                                attachmentResponse.getMimetype(),
                                attachmentResponse.getUrl(),
                                attachmentResponse.getCreatedAt(),
                                attachmentResponse.getUpdatedAt()
                        );
                        localDataSource.insertAttachment(attachmentEntity);
                    }
                    petEntities.add(petEntity);
                }
                localDataSource.insertPetsAndDelete(petEntities);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<PetEntity>>> searchPets(String keyword) {
        return new NetworkBoundResource<List<PetEntity>, List<PetResponse>>(appExecutors) {
            @Override
            protected LiveData<List<PetEntity>> loadFromDB() {
                return localDataSource.searchPets(keyword);
            }

            @Override
            protected Boolean shouldFetch(List<PetEntity> data) {
                return NetworkUtils.isConnectedFast(context);
            }

            @Override
            protected LiveData<ApiResponse<List<PetResponse>>> createCall() {
                return remoteDataSource.searchPets(keyword);
            }

            @Override
            protected void saveCallResult(List<PetResponse> petResponses) {
                ArrayList<PetEntity> petEntities = new ArrayList<>();
                for (PetResponse response : petResponses) {
                    PetEntity petEntity = new PetEntity(
                            response.getId(),
                            response.getUsia(),
                            response.getKondisi(),
                            response.getRasId(),
                            response.getRas().getRas(),
                            response.getUserId(),
                            response.getUser().getFullName(),
                            response.getUser().getAlamat().getProvinsiName(),
                            response.getNamaHewan(),
                            response.getBeratBadan(),
                            response.getHarga(),
                            response.getJenisKelamin(),
                            response.getAttachmentId(),
                            response.getAttachment().getUrl(),
                            response.getDeskripsi(),
                            0,
                            response.getCreatedAt(),
                            response.getUpdatedAt()
                    );
                    AlamatEntity alamatEntity = new AlamatEntity(
                            response.getUser().getAlamatId(),
                            response.getUser().getAlamat().getAlamat(),
                            response.getUser().getAlamat().getKelurahanName(),
                            response.getUser().getAlamat().getKelurahanId(),
                            response.getUser().getAlamat().getKecamatanName(),
                            response.getUser().getAlamat().getKecamatanId(),
                            response.getUser().getAlamat().getKotaName(),
                            response.getUser().getAlamat().getKotaId(),
                            response.getUser().getAlamat().getProvinsiName(),
                            response.getUser().getAlamat().getProvinsiId()
                    );
                    petEntities.add(petEntity);
                    localDataSource.insertAlamat(alamatEntity);
                }
                localDataSource.insertPets(petEntities);
            }
        }.asLiveData();
    }

    public LiveData<List<PetEntity>> getFavoritePets() {
        return localDataSource.ameguDatabase.petDao().getFavorites();
    }

    public LiveData<Resource<List<PetEntity>>> getMyPets(String token) {
        return new NetworkBoundResource<List<PetEntity>, List<PetResponse>>(appExecutors) {
            @Override
            protected LiveData<List<PetEntity>> loadFromDB() {
                return localDataSource.getPets();
            }

            @Override
            protected Boolean shouldFetch(List<PetEntity> data) {
                return true;
            }

            @Override
            protected LiveData<ApiResponse<List<PetResponse>>> createCall() {
                return remoteDataSource.getMyPets(token);
            }

            @Override
            protected void saveCallResult(List<PetResponse> petResponses) {
                ArrayList<PetEntity> petEntities = new ArrayList<>();
                for (PetResponse response : petResponses) {
                    PetEntity petEntity = new PetEntity(
                            response.getId(),
                            response.getUsia(),
                            response.getKondisi(),
                            response.getRasId(),
                            response.getRas().getRas(),
                            response.getUserId(),
                            response.getUser().getFullName(),
                            response.getUser().getAlamat().getProvinsiName(),
                            response.getNamaHewan(),
                            response.getBeratBadan(),
                            response.getHarga(),
                            response.getJenisKelamin(),
                            response.getAttachmentId(),
                            response.getAttachment().getUrl(),
                            response.getDeskripsi(),
                            0,
                            response.getCreatedAt(),
                            response.getUpdatedAt()
                    );
                    petEntities.add(petEntity);
                }
                localDataSource.insertPetsAndDelete(petEntities);
            }
        }.asLiveData();
    }

    public LiveData<Resource<PetEntity>> getPetDetail(long petId) {
        return new NetworkBoundResource<PetEntity, PetResponse>(appExecutors) {
            @Override
            protected LiveData<PetEntity> loadFromDB() {
                return localDataSource.getPetDetail(petId);
            }

            @Override
            protected Boolean shouldFetch(PetEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<PetResponse>> createCall() {
                return remoteDataSource.getPetDetail(petId);
            }

            @Override
            protected void saveCallResult(PetResponse response) {
                ArrayList<PetEntity> petEntities = new ArrayList<>();
                PetEntity petEntity = new PetEntity(response.getId(),
                        response.getUsia(),
                        response.getKondisi(),
                        response.getRasId(),
                        response.getRas().getRas(),
                        response.getUserId(),
                        response.getUser().getFullName(),
                        response.getUser().getAlamat().getProvinsiName(),
                        response.getNamaHewan(),
                        response.getBeratBadan(),
                        response.getHarga(),
                        response.getJenisKelamin(),
                        response.getAttachmentId(),
                        response.getAttachment().getUrl(),
                        response.getDeskripsi(),
                        0,
                        response.getCreatedAt(),
                        response.getUpdatedAt()
                );
                petEntities.add(petEntity);
                localDataSource.insertPets(petEntities);
            }
        }.asLiveData();
    }

    public LiveData<List<SearchEntity>> getSearchData() {
        return localDataSource.ameguDatabase.searchDao().getAllSearchHistory();
    }

    public void removeSearchData() {
        appExecutors.diskIO().execute(() -> localDataSource.ameguDatabase.searchDao().deleteAll());
    }

    public void updateFavorite(int isFavorite, long petId) {
        appExecutors.diskIO().execute(() -> localDataSource.ameguDatabase.petDao().updateFavorite(isFavorite, petId));
    }

    public void postSearchData(String keyword) {
        appExecutors.diskIO().execute(() -> {
            SearchEntity checkHistory = localDataSource.ameguDatabase.searchDao().getDataByKeyword(keyword);
            if (checkHistory == null) {
                SearchEntity searchEntity = new SearchEntity(keyword);
                localDataSource.ameguDatabase.searchDao().insert(searchEntity);
                return;
            }
            checkHistory.setUpdatedAt(new Date().getTime());
            localDataSource.ameguDatabase.searchDao().update(checkHistory);
        });
    }

    private int imageId = 0;
    public LiveData<Resource<AttachmentEntity>> uploadFile(File file, String token) {
        return new NetworkBoundResource<AttachmentEntity, AttachmentResponse>(appExecutors) {
            @Override
            protected LiveData<AttachmentEntity> loadFromDB() {
                return localDataSource.ameguDatabase.attachmentDao().getAttachment(imageId);
            }

            @Override
            protected Boolean shouldFetch(AttachmentEntity data) {
                return true;
            }

            @Override
            protected LiveData<ApiResponse<AttachmentResponse>> createCall() {
                return remoteDataSource.uploadFile(file, token);
            }

            @Override
            protected void saveCallResult(AttachmentResponse response) {
                AttachmentEntity attachmentEntity = new AttachmentEntity(
                        response.getId(),
                        response.getUserId(),
                        response.getHewanId(),
                        response.getFilename(),
                        response.getMimetype(),
                        response.getUrl(),
                        response.getCreatedAt(),
                        response.getUpdatedAt()
                );
                imageId = response.getId();
                localDataSource.insertAttachment(attachmentEntity);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<JenisEntity>>> getJenis() {
        return new NetworkBoundResource<List<JenisEntity>, List<JenisResponse>>(appExecutors) {
            @Override
            protected LiveData<List<JenisEntity>> loadFromDB() {
                return localDataSource.ameguDatabase.jenisDao().getAllJenisEntity();
            }

            @Override
            protected Boolean shouldFetch(List<JenisEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<JenisResponse>>> createCall() {
                return remoteDataSource.getJenis();
            }

            @Override
            protected void saveCallResult(List<JenisResponse> responses) {
                ArrayList<JenisEntity> jenisEntities = new ArrayList<>();
                for (JenisResponse response : responses) {
                    JenisEntity jenisEntity = new JenisEntity(response.getId(), response.getJenis());
                    jenisEntities.add(jenisEntity);
                }
                localDataSource.ameguDatabase.jenisDao().insert(jenisEntities);
            }
        }.asLiveData();
    }

    public LiveData<Resource<List<RasEntity>>> getRas(long id) {
        return new NetworkBoundResource<List<RasEntity>, List<RasResponse>>(appExecutors) {
            @Override
            protected LiveData<List<RasEntity>> loadFromDB() {
                return localDataSource.ameguDatabase.rasDao().getAllRasEntity();
            }

            @Override
            protected Boolean shouldFetch(List<RasEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<RasResponse>>> createCall() {
                return remoteDataSource.getRas(id);
            }

            @Override
            protected void saveCallResult(List<RasResponse> responses) {
                ArrayList<RasEntity> rasEntities = new ArrayList<>();
                for (RasResponse response : responses) {
                    RasEntity rasEntity = new RasEntity(response.getId(), response.getRas(), response.getJenisId());
                    rasEntities.add(rasEntity);
                }
                localDataSource.ameguDatabase.rasDao().insert(rasEntities);
            }
        }.asLiveData();
    }

    public LiveData<String> uploadPet(long rasId, String namaHewan, int usia, int beratBadan, String kondisi, String jenisKelamin, int harga, String deskripsi, long attachmentId, String token) {
        return remoteDataSource.uploadPet(
                rasId,
                namaHewan,
                usia,
                beratBadan,
                kondisi,
                jenisKelamin,
                harga,
                deskripsi,
                attachmentId,
                token
        );
    }

    public LiveData<String> deleteHewan(long id, String token) {
        return remoteDataSource.deletePet(id, token);
    }

    public LiveData<AttachmentEntity> getAttachment(int attachmentId) {
        return localDataSource.getAttachment(attachmentId);
    }
}
