package id.rezajuliandri.amegu.ui.address;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import id.rezajuliandri.amegu.data.api.ApiConfig;
import id.rezajuliandri.amegu.data.entity.responses.EmptyOkResponse;
import id.rezajuliandri.amegu.data.entity.responses.KecamatanResponse;
import id.rezajuliandri.amegu.data.entity.responses.KelurahanResponse;
import id.rezajuliandri.amegu.data.entity.responses.KotaResponse;
import id.rezajuliandri.amegu.data.entity.responses.ProvinsiResponse;
import id.rezajuliandri.amegu.data.entity.location.Kecamatan;
import id.rezajuliandri.amegu.data.entity.location.Kelurahan;
import id.rezajuliandri.amegu.data.entity.location.Kota;
import id.rezajuliandri.amegu.data.entity.location.Provinsi;
import id.rezajuliandri.amegu.data.database.UsersRepository;
import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.data.entity.auth.Users;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import id.rezajuliandri.amegu.interfaces.location.OnAlamatSent;
import id.rezajuliandri.amegu.interfaces.location.OnKecamatan;
import id.rezajuliandri.amegu.interfaces.location.OnKelurahan;
import id.rezajuliandri.amegu.interfaces.location.OnKota;
import id.rezajuliandri.amegu.interfaces.location.OnProvinsi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressViewModel extends ViewModel {

    Application application;
    UsersRepository mRepository;
    Session session;

    public AddressViewModel(Application application) {
        this.application = application;
        mRepository = new UsersRepository(application);
        session = new Session(application);
    }

    /**
     * Mengambil data provinsi
     *
     * @param onProvinsi Callback yang digunakan jika action sudah dilakukan.
     */
    public final void getProvinsi(OnProvinsi onProvinsi) {
        Call<ProvinsiResponse> provinsiResponseCall = ApiConfig.getApiService().getProvinsi();
        provinsiResponseCall.enqueue(new Callback<ProvinsiResponse>() {
            @Override
            public void onResponse(Call<ProvinsiResponse> call, Response<ProvinsiResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ProvinsiResponse provinsiResponse = response.body();
                        ArrayList<Provinsi> provinsiList = provinsiResponse.getData();
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

    /**
     * Mengambil data kota berdasarkan provinsiId
     *
     * @param onKota     Callback yang digunakan jika action sudah dilakukan
     * @param provinsiId Parameter provinsiId yang dibutuhkan untuk mendapatkan data kota
     */
    public final void getKota(OnKota onKota, long provinsiId) {
        Call<KotaResponse> kotaResponseCall = ApiConfig.getApiService().getKota(provinsiId);
        kotaResponseCall.enqueue(new Callback<KotaResponse>() {
            @Override
            public void onResponse(Call<KotaResponse> call, Response<KotaResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KotaResponse kotaResponse = response.body();
                        ArrayList<Kota> kotaList = kotaResponse.getData();
                        onKota.success(kotaList);
                    }
                } else {
                    onKota.error("onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {
                onKota.error(t.getMessage());
            }
        });
    }

    /**
     * Mengambil data kecamatan berdasarkan kotaId
     *
     * @param onKecamatan Callback yang digunakan jika action sudah dilakukan
     * @param kotaId      Parameter kotaId yang digunakan untuk mengambil kotaId
     */
    public final void getKecamatan(OnKecamatan onKecamatan, long kotaId) {
        Call<KecamatanResponse> kecamatanResponseCall = ApiConfig.getApiService().getKecamatan(kotaId);
        kecamatanResponseCall.enqueue(new Callback<KecamatanResponse>() {
            @Override
            public void onResponse(Call<KecamatanResponse> call, Response<KecamatanResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KecamatanResponse kecamatanResponse = response.body();
                        ArrayList<Kecamatan> kecamatanList = kecamatanResponse.getData();
                        onKecamatan.success(kecamatanList);
                    }
                } else {
                    onKecamatan.error("onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                onKecamatan.error(t.getMessage());
            }
        });
    }

    /**
     * Mengambil data kelurahan berdasarkan kecamatanId
     *
     * @param onKelurahan Callback yang digunakan jika action berhasil dilakukan
     * @param kecamatanId Parameter kecamatanId yang digunakan untuk mendapatkan data kelurahan
     */
    public final void getKelurahan(OnKelurahan onKelurahan, long kecamatanId) {
        Call<KelurahanResponse> kelurahanResponseCall = ApiConfig.getApiService().getKelurahan(kecamatanId);
        kelurahanResponseCall.enqueue(new Callback<KelurahanResponse>() {
            @Override
            public void onResponse(Call<KelurahanResponse> call, Response<KelurahanResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KelurahanResponse kelurahanResponse = response.body();
                        ArrayList<Kelurahan> kelurahanList = kelurahanResponse.getData();
                        onKelurahan.success(kelurahanList);
                    }
                } else {
                    onKelurahan.error("onFailure: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                onKelurahan.error(t.getMessage());
            }
        });
    }

    /**
     * Mengirimkan alamat user kedalam database dan merefresh data yang ada pada lokal
     *
     * @param onAlamatSent Callback yang dipanggil jika action sudah dilakukan
     * @param alamat       Data alamat yang dikirimkan ke server
     * @param kelurahan    Class kelurahan yang digunakan untuk dikirimkan ke server
     */
    public final void sendAlamat(OnAlamatSent onAlamatSent, String alamat, Kelurahan kelurahan) {
        session.getToken(new OnToken() {
            @Override
            public void success(String token) {
                try {
                    Call<EmptyOkResponse> emptyOkResponseCall = ApiConfig.
                            getApiService().
                            sendAlamat(
                                    alamat,
                                    kelurahan.getId(),
                                    kelurahan.getKecamatan().getId(),
                                    kelurahan.getKecamatan().getKota().getId(),
                                    kelurahan.getKecamatan().getKota().getProvinsi().getId(),
                                    token
                            );
                    emptyOkResponseCall.enqueue(new Callback<EmptyOkResponse>() {
                        @Override
                        public void onResponse(Call<EmptyOkResponse> call, Response<EmptyOkResponse> response) {
                            session.refreshUserData(new OnProfile() {
                                @Override
                                public void success(Users users) {
                                    onAlamatSent.success();

                                }

                                @Override
                                public void error(String message) {
                                    onAlamatSent.error(message);
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<EmptyOkResponse> call, Throwable t) {
                            onAlamatSent.error(t.getMessage());
                        }
                    });
                } catch (Exception e){
                    onAlamatSent.error(e.getMessage());
                }
            }

            @Override
            public void error(String error) {
                onAlamatSent.error(error);
            }
        });
    }
}
