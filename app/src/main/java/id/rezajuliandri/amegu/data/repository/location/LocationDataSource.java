package id.rezajuliandri.amegu.data.repository.location;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.vo.Resource;

public interface LocationDataSource {
    LiveData<Resource<List<ProvinsiEntity>>> getAllProvince();

    LiveData<Resource<List<KotaEntity>>> getAllKotaByProvinsi(long provinsiId);

    LiveData<Resource<List<KecamatanEntity>>> getAllKecamatanByKota(long kotaId);

    LiveData<Resource<List<KelurahanEntity>>> getAllKelurahanByKecamatan(long kecamatanId);
}
