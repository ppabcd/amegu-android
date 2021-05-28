package id.rezajuliandri.amegu.data.local;

import androidx.lifecycle.LiveData;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.data.local.entity.user.BankAccountEntity;
import id.rezajuliandri.amegu.data.local.entity.user.BankEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.data.local.room.AmeguDatabase;

/**
 * Bagian yang menghandle proses data ke database lokal
 */
public class LocalDataSource {
    private static LocalDataSource INSTANCE = null;
    public AmeguDatabase ameguDatabase;
    ArrayList<Long> listFavorite = new ArrayList<>();

    public LocalDataSource(AmeguDatabase ameguDatabase) {
        this.ameguDatabase = ameguDatabase;
    }

    public static LocalDataSource getInstance(AmeguDatabase ameguDatabase) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(ameguDatabase);
        }
        return INSTANCE;
    }

    public LiveData<List<ProvinsiEntity>> getAllProvinsi() {
        return ameguDatabase.provinsiDao().getProvinsi();
    }

    public void insertProvinsi(List<ProvinsiEntity> provinsiEntityList) {
        ameguDatabase.provinsiDao().insert(provinsiEntityList);
    }

    public LiveData<List<KotaEntity>> getKota(long provinsiId) {
        return ameguDatabase.kotaDao().getKota(provinsiId);
    }

    public void insertKota(List<KotaEntity> kotaEntityList) {
        ameguDatabase.kotaDao().insert(kotaEntityList);
    }

    public LiveData<List<KecamatanEntity>> getKecamatan(long kotaId) {
        return ameguDatabase.kecamatanDao().getKecamatan(kotaId);
    }

    public void insertKecamatan(List<KecamatanEntity> kecamatanEntityList) {
        ameguDatabase.kecamatanDao().insert(kecamatanEntityList);
    }

    public LiveData<List<KelurahanEntity>> getKelurahan(long kecamatanId) {
        return ameguDatabase.kelurahanDao().getKelurahan(kecamatanId);
    }

    public void insertKelurahan(List<KelurahanEntity> kelurahanEntityList) {
        ameguDatabase.kelurahanDao().insert(kelurahanEntityList);
    }

    public void insertUser(UserEntity userEntity) {
        ameguDatabase.userDao().insert(userEntity);
    }

    public void deleteUser() {
        ameguDatabase.userDao().delete();
    }

    public LiveData<UserEntity> getUser() {
        return ameguDatabase.userDao().getUser();
    }

    public void insertPetsAndDelete(ArrayList<PetEntity> petEntities) {
        List<PetEntity> listFavoritePets = ameguDatabase.petDao().getFavoritesEntities();
        for (PetEntity pet : listFavoritePets) {
            listFavorite.add(pet.getId());
        }
        ameguDatabase.petDao().delete();
        this.insertPets(petEntities);
    }

    public void insertPets(ArrayList<PetEntity> petEntities) {
        ArrayList<PetEntity> insertData = new ArrayList<>();
        ArrayList<PetEntity> updateData = new ArrayList<>();
        for (PetEntity entity : petEntities) {
            PetEntity checkPet = ameguDatabase.petDao().getPetDetailEntity(entity.getId());
            if (listFavorite.contains(entity.getId())) {
                entity.setIsFavorite(1);
            }
            if (checkPet == null) {
                insertData.add(entity);
                continue;
            }
            entity.setIsFavorite(checkPet.getIsFavorite());
            updateData.add(entity);
        }
        ameguDatabase.petDao().insert(insertData);
        ameguDatabase.petDao().update(updateData);
    }

    public LiveData<List<PetEntity>> getPets() {
        return ameguDatabase.petDao().getPets();
    }

    public LiveData<PetEntity> getPetDetail(long petId) {
        return ameguDatabase.petDao().getPetDetail(petId);
    }

    public LiveData<List<PetEntity>> searchPets(String keyword) {
        return ameguDatabase.petDao().searchPet("%" + keyword + "%");
    }

    public void insertAlamat(AlamatEntity alamatEntity) {
        ameguDatabase.alamatDao().insert(alamatEntity);
    }

    public LiveData<AlamatEntity> getAlamat(int alamatId) {
        return ameguDatabase.alamatDao().getAlamat(alamatId);
    }

    public void insertAttachment(AttachmentEntity attachmentEntity) {
        ameguDatabase.attachmentDao().insert(attachmentEntity);
    }


    public LiveData<AttachmentEntity> getAttachment(int attachmentId) {
        return ameguDatabase.attachmentDao().getAttachment(attachmentId);
    }

    public void insertRas(ArrayList<RasEntity> rasEntities) {
        ameguDatabase.rasDao().insert(rasEntities);
    }

    public LiveData<RasEntity> getRasLocal(int rasId) {
        return ameguDatabase.rasDao().getRasEntity(rasId);
    }

    public void insertBankAccount(BankAccountEntity bankAccountEntity) {
        ameguDatabase.bankAccountDao().insert(bankAccountEntity);
    }

    public void insertBank(BankEntity bankEntity) {
        ameguDatabase.bankDao().insert(bankEntity);
    }
}