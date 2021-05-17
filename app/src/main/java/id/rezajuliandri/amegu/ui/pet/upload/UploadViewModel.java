package id.rezajuliandri.amegu.ui.pet.upload;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.JenisEntity;
import id.rezajuliandri.amegu.data.local.entity.pet.RasEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class UploadViewModel extends ViewModel {
    AmeguRepository ameguRepository;
    public UploadViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<Resource<AttachmentEntity>> postImage(File file, String token) {
        return ameguRepository.petRepository().uploadFile(file, token);
    }

    public LiveData<Resource<List<JenisEntity>>> getJenis() {
        return ameguRepository.petRepository().getJenis();
    }

    public LiveData<Resource<List<RasEntity>>> getRas(long id) {
        return ameguRepository.petRepository().getRas(id);
    }

    public LiveData<String> uploadPet(long rasId,
                                      String namaHewan,
                                      int usia,
                                      int beratBadan,
                                      String kondisi,
                                      String jenisKelamin,
                                      int harga,
                                      String deskripsi,
                                      long attachmentId,
                                      String token) {
        return ameguRepository.petRepository().uploadPet( rasId,
                namaHewan,
                usia,
                beratBadan,
                kondisi,
                jenisKelamin,
                harga,
                deskripsi,
                attachmentId,
                token);
    }
}
