package id.rezajuliandri.amegu.ui.user.address;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

public class AddressUserViewModel  extends ViewModel {
    AmeguRepository ameguRepository;
    public AddressUserViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }


    public LiveData<Resource<List<ProvinsiEntity>>> getProvinsi() {
        return ameguRepository.locationRepository().getAllProvince();
    }

    public LiveData<Resource<List<KotaEntity>>> getKota(long provinsiId) {
        return ameguRepository.locationRepository().getAllKotaByProvinsi(provinsiId);
    }

    public LiveData<Resource<List<KecamatanEntity>>> getKecamatan(long kotaId) {
        return ameguRepository.locationRepository().getAllKecamatanByKota(kotaId);
    }

    public LiveData<Resource<List<KelurahanEntity>>> getKelurahan(long kelurahanId) {
        return ameguRepository.locationRepository().getAllKelurahanByKecamatan(kelurahanId);
    }
    // TODO Update sendAlamat
    public LiveData<String> sendAlamat(
            String alamat,
            long provinsiId,
            long kotaId,
            long kecamatanId,
            long kelurahanId,
            String token
    ) {
        return ameguRepository.locationRepository().postAlamat(alamat, provinsiId, kotaId, kecamatanId, kelurahanId, token);
    }

    public LiveData<UserEntity> getUser() {
        return ameguRepository.userRepository().getUser();
    }

    public LiveData<Resource<AlamatEntity>> getAlamat(int alamatId, String token) {
        return ameguRepository.locationRepository().getAlamat(alamatId, token);
    }

    public LiveData<Resource<UserEntity>> getProfile(String token) {
        return ameguRepository.userRepository().updateProfile(token);
    }
}
