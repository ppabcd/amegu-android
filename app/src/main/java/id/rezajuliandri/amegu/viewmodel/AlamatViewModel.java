package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import java.util.List;

import id.rezajuliandri.amegu.api.ApiConfig;
import id.rezajuliandri.amegu.api.responses.KecamatanResponse;
import id.rezajuliandri.amegu.api.responses.KelurahanResponse;
import id.rezajuliandri.amegu.api.responses.KotaResponse;
import id.rezajuliandri.amegu.api.responses.ProvinsiResponse;
import id.rezajuliandri.amegu.api.responses.data.Kecamatan;
import id.rezajuliandri.amegu.api.responses.data.Kelurahan;
import id.rezajuliandri.amegu.api.responses.data.Kota;
import id.rezajuliandri.amegu.api.responses.data.Provinsi;
import id.rezajuliandri.amegu.interfaces.location.OnKecamatan;
import id.rezajuliandri.amegu.interfaces.location.OnKelurahan;
import id.rezajuliandri.amegu.interfaces.location.OnKota;
import id.rezajuliandri.amegu.interfaces.location.OnProvinsi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlamatViewModel extends ViewModel {

    Application application;
    public AlamatViewModel(Application application){
        this.application = application;
    }

    public final void getProvinsi(OnProvinsi onProvinsi){
        Call<ProvinsiResponse> provinsiResponseCall = ApiConfig.getApiService().getProvinsi();
        provinsiResponseCall.enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ProvinsiResponse provinsiResponse = response.body();
                        List<Provinsi> provinsiList = provinsiResponse.getData();
                        onProvinsi.success(provinsiList);
                    }
                } else {
                    onProvinsi.error("onFailure:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<ProvinsiResponse> call, Throwable t) {
                onProvinsi.error(t.getMessage());
            }
        });
    }
    public final void getKota(OnKota onKota, int provinsiId){
        Call<KotaResponse> kotaResponseCall = ApiConfig.getApiService().getKota(provinsiId);
        kotaResponseCall.enqueue(new Callback<KotaResponse>() {
            @Override
            public void onResponse(Call<KotaResponse> call, Response<KotaResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        KotaResponse kotaResponse = response.body();
                        List<Kota> kotaList= kotaResponse.getData();
                        onKota.success(kotaList);
                    }
                } else {
                    onKota.error("onFailure: "+ response.message());
                }
            }

            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {
                onKota.error(t.getMessage());
            }
        });
    }
    public final void getKecamatan(OnKecamatan onKecamatan, int kotaId){
        Call<KecamatanResponse> kecamatanResponseCall = ApiConfig.getApiService().getKecamatan(kotaId);
        kecamatanResponseCall.enqueue(new Callback<KecamatanResponse>() {
            @Override
            public void onResponse(Call<KecamatanResponse> call, Response<KecamatanResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        KecamatanResponse kecamatanResponse = response.body();
                        List<Kecamatan> kecamatanList= kecamatanResponse.getData();
                        onKecamatan.success(kecamatanList);
                    }
                } else {
                    onKecamatan.error("onFailure: "+ response.message());
                }
            }

            @Override
            public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                onKecamatan.error(t.getMessage());
            }
        });
    }
    public final void getKelurahan(OnKelurahan onKelurahan, int kecamatanId){
        Call<KelurahanResponse> kelurahanResponseCall = ApiConfig.getApiService().getKelurahan(kecamatanId);
        kelurahanResponseCall.enqueue(new Callback<KelurahanResponse>() {
            @Override
            public void onResponse(Call<KelurahanResponse> call, Response<KelurahanResponse> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        KelurahanResponse kelurahanResponse = response.body();
                        List<Kelurahan> kelurahanList= kelurahanResponse.getData();
                        onKelurahan.success(kelurahanList);
                    }
                } else {
                    onKelurahan.error("onFailure: "+ response.message());
                }
            }

            @Override
            public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                onKelurahan.error(t.getMessage());
            }
        });
    }
}
