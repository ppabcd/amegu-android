package id.rezajuliandri.amegu.data.repository.pet;

import androidx.lifecycle.LiveData;

import java.io.File;
import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.SearchEntity;
import id.rezajuliandri.amegu.vo.Resource;

/**
 * Ringkasan class PetRepository
 */
public interface PetDataSource {
    LiveData<Resource<List<PetEntity>>> getPets();

    LiveData<Resource<List<PetEntity>>> searchPets(String keyword);

    LiveData<List<PetEntity>> getFavoritePets();

    LiveData<Resource<List<PetEntity>>> getMyPets(String token);

    LiveData<Resource<PetEntity>> getPetDetail(long petId);

    LiveData<List<SearchEntity>> getSearchData();

    void removeSearchData();

    void updateFavorite(int isFavorite, long petId);

    void postSearchData(String keyword);

    LiveData<Resource<AttachmentEntity>> uploadFile(File file, String token);

    LiveData<Resource<List<JenisEntity>>> getJenis();

    LiveData<Resource<List<RasEntity>>> getRas(long id);

    LiveData<String> uploadPet(long rasId, String namaHewan, int usia, int beratBadan, String kondisi, String jenisKelamin, int harga, String deskripsi, long attachmentId, String token);

    LiveData<String> deleteHewan(long id, String token);

    LiveData<String> updatePet(long id, long rasId, String namaHewan, int usia, int beratBadan, String kondisi, String jenisKelamin, int harga, String deskripsi, long attachmentId, String token);

    LiveData<String> adopt(long petId, String token);
}
