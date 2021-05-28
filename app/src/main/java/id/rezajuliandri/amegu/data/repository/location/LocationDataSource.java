package id.rezajuliandri.amegu.data.repository.location;

import androidx.lifecycle.LiveData;

import java.util.List;

import id.rezajuliandri.amegu.data.local.entity.location.KecamatanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KelurahanEntity;
import id.rezajuliandri.amegu.data.local.entity.location.KotaEntity;
import id.rezajuliandri.amegu.data.local.entity.location.ProvinsiEntity;
import id.rezajuliandri.amegu.vo.Resource;

/**
 * Ringkasan class LocationRepository
 */
public interface LocationDataSource {
    /**
     * Mengambil data provinsi
     *
     * @return LiveData<Resource < List < ProvinsiEntity>>>
     */
    LiveData<Resource<List<ProvinsiEntity>>> getAllProvince();

    /**
     * Mengambil data kota
     *
     * @param provinsiId Id Provinsi untuk mengambil data kota
     * @return LiveData<Resource < List < KotaEntity>>>
     */
    LiveData<Resource<List<KotaEntity>>> getAllKotaByProvinsi(long provinsiId);

    /**
     * Mengambil data kecamatan
     *
     * @param kotaId Id Kota untuk mengambil data kecamatan
     * @return LiveData<Resource < List < KecamatanEntity>>>
     */
    LiveData<Resource<List<KecamatanEntity>>> getAllKecamatanByKota(long kotaId);

    /**
     * Menbgambil data kelurahan
     *
     * @param kecamatanId Id Kelurahan untuk mengambil data kecamatan
     * @return LiveData<Resource < List < KelurahanEntity>>>
     */
    LiveData<Resource<List<KelurahanEntity>>> getAllKelurahanByKecamatan(long kecamatanId);
}
