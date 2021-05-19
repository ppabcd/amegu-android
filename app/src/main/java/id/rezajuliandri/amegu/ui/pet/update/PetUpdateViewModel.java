package id.rezajuliandri.amegu.ui.pet.update;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.PetEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class PetUpdateViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public PetUpdateViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<Resource<PetEntity>> getPetDetail(long petId) {
        return ameguRepository.petRepository().getPetDetail(petId);
    }

    public LiveData<Resource<List<RasEntity>>> getRas(long jenisId) {
        return ameguRepository.petRepository().getRas(jenisId);
    }

    public LiveData<Resource<List<JenisEntity>>> getJenis() {
        return ameguRepository.petRepository().getJenis();
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<Resource<AttachmentEntity>> postImage(File file, String token) {
        return ameguRepository.petRepository().uploadFile(file, token);
    }

    public LiveData<AttachmentEntity> getAttachment(int attachmentId) {
        return ameguRepository.petRepository().getAttachment(attachmentId);
    }

    public LiveData<RasEntity> getRasLocal(int rasId) {
        return ameguRepository.petRepository().getRasLocal(rasId);
    }

    public LiveData<String> uploadPet(long id, long rasId, String namaHewan, int usia, int beratBadan, String kondisi, String jenisKelamin, int harga, String deskripsi, long attachmentId, String token) {
        return ameguRepository.petRepository().updatePet(id, rasId, namaHewan, usia, beratBadan, kondisi, jenisKelamin, harga, deskripsi, attachmentId, token);
    }
}
