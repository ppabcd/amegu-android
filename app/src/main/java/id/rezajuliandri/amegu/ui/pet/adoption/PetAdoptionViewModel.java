package id.rezajuliandri.amegu.ui.pet.adoption;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.io.File;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.pet.AttachmentEntity;
import id.rezajuliandri.amegu.data.local.entity.user.AdopsiEntity;
import id.rezajuliandri.amegu.data.local.entity.user.InvoiceEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class PetAdoptionViewModel extends ViewModel {
    AmeguRepository ameguRepository;
    public PetAdoptionViewModel(AmeguRepository ameguRepository){
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<Resource<AdopsiEntity>> getAdoptionDetail(long petId, String token) {
        return ameguRepository.petRepository().getAdoptionDetail(petId, token);
    }

    public LiveData<InvoiceEntity> getInvoice(int id) {
        return ameguRepository.petRepository().getInvoiceByAdopsiId(id);
    }

    public LiveData<Resource<AttachmentEntity>> postImage(File file, String token) {
        return ameguRepository.petRepository().uploadFile(file, token);
    }

    public LiveData<String> paymentConfirm(int hewanId, int fileId, String token) {
        return ameguRepository.petRepository().paymentConfirm(hewanId, fileId, token);
    }
}
