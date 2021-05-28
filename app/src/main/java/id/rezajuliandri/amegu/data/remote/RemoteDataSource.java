package id.rezajuliandri.amegu.data.remote;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.File;
import java.util.List;

import id.rezajuliandri.amegu.data.remote.api.ApiConfig;
import id.rezajuliandri.amegu.data.remote.response.SimpleResponse;
import id.rezajuliandri.amegu.data.remote.response.attachment.upload.AttachmentResponse;
import id.rezajuliandri.amegu.data.remote.response.attachment.upload.UploadResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.login.LoginResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.login.UserLoginResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.ProfileResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.UserProfileResponse;
import id.rezajuliandri.amegu.data.remote.response.location.alamat.AlamatResponse;
import id.rezajuliandri.amegu.data.remote.response.location.alamat.AlamatResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.kecamatan.KecamatanResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kecamatan.KecamatanResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.kelurahan.KelurahanResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kelurahan.KelurahanResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.kota.KotaResponse;
import id.rezajuliandri.amegu.data.remote.response.location.kota.KotaResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.provinsi.ProvinsiResponse;
import id.rezajuliandri.amegu.data.remote.response.location.provinsi.ProvinsiResponseParent;
import id.rezajuliandri.amegu.data.remote.response.pet.adopsidetail.AdopsiDetailResponseParent;
import id.rezajuliandri.amegu.data.remote.response.pet.adopsidetail.AdopsiResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.detail.PetDetailResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.jenis.JenisResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.jenis.JenisResponseParent;
import id.rezajuliandri.amegu.data.remote.response.pet.pets.PetResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.pets.PetsResponseParent;
import id.rezajuliandri.amegu.data.remote.response.pet.ras.RasResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.ras.RasResponseParent;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Bagian yang digunakan untuk memanage permintaan data ke API
 */
public class RemoteDataSource {
    private static RemoteDataSource INSTANCE;

    public static RemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource();
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<ProvinsiResponse>>> getProvinsi() {
        MutableLiveData<ApiResponse<List<ProvinsiResponse>>> resultProvinsi = new MutableLiveData<>();
        Call<ProvinsiResponseParent> call = ApiConfig.getApiService().getProvinsi();
        call.enqueue(new Callback<ProvinsiResponseParent>() {
            @Override
            public void onResponse(Call<ProvinsiResponseParent> call, Response<ProvinsiResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ProvinsiResponseParent list = response.body();
                        resultProvinsi.setValue(ApiResponse.success(list.getData()));
                    }
                } else {
                    resultProvinsi.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<ProvinsiResponseParent> call, Throwable t) {
                resultProvinsi.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultProvinsi;
    }

    public LiveData<ApiResponse<List<KotaResponse>>> getKota(long provinsiId) {
        MutableLiveData<ApiResponse<List<KotaResponse>>> resultKota = new MutableLiveData<>();
        Call<KotaResponseParent> call = ApiConfig.getApiService().getKota(provinsiId);
        call.enqueue(new Callback<KotaResponseParent>() {
            @Override
            public void onResponse(Call<KotaResponseParent> call, Response<KotaResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KotaResponseParent list = response.body();
                        resultKota.setValue(ApiResponse.success(list.getData()));
                    }
                } else {
                    resultKota.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<KotaResponseParent> call, Throwable t) {
                resultKota.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultKota;
    }

    public LiveData<ApiResponse<List<KecamatanResponse>>> getKecamatan(long kotaId) {
        MutableLiveData<ApiResponse<List<KecamatanResponse>>> resultKecamatan = new MutableLiveData<>();
        Call<KecamatanResponseParent> call = ApiConfig.getApiService().getKecamatan(kotaId);
        call.enqueue(new Callback<KecamatanResponseParent>() {
            @Override
            public void onResponse(Call<KecamatanResponseParent> call, Response<KecamatanResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KecamatanResponseParent list = response.body();
                        resultKecamatan.setValue(ApiResponse.success(list.getData()));
                    }
                } else {
                    resultKecamatan.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<KecamatanResponseParent> call, Throwable t) {
                resultKecamatan.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultKecamatan;
    }

    public LiveData<ApiResponse<List<KelurahanResponse>>> getKelurahan(long kecamatanId) {
        MutableLiveData<ApiResponse<List<KelurahanResponse>>> resultKelurahan = new MutableLiveData<>();
        Call<KelurahanResponseParent> call = ApiConfig.getApiService().getKelurahan(kecamatanId);
        call.enqueue(new Callback<KelurahanResponseParent>() {
            @Override
            public void onResponse(Call<KelurahanResponseParent> call, Response<KelurahanResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        KelurahanResponseParent list = response.body();
                        resultKelurahan.setValue(ApiResponse.success(list.getData()));
                    }
                } else {
                    resultKelurahan.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<KelurahanResponseParent> call, Throwable t) {
                resultKelurahan.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultKelurahan;
    }

    public LiveData<ApiResponse<UserLoginResponse>> login(String username, String password) {
        MutableLiveData<ApiResponse<UserLoginResponse>> resultLogin = new MutableLiveData<>();
        Call<LoginResponse> call = ApiConfig.getApiService().login(username, password);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        LoginResponse responses = response.body();
                        resultLogin.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultLogin.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                resultLogin.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultLogin;
    }

    public LiveData<String> register(String firstname, String lastName, String email, String password, String phoneNumber) {
        MutableLiveData<String> resultRegister = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().register(
                firstname, lastName, email, password, phoneNumber
        );
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultRegister.setValue(response.message());
                    }
                } else {
                    resultRegister.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultRegister.setValue(t.getMessage());
            }
        });
        return resultRegister;
    }

    public LiveData<ApiResponse<UserProfileResponse>> getProfile(String token) {
        MutableLiveData<ApiResponse<UserProfileResponse>> resultProfile = new MutableLiveData<>();
        Call<ProfileResponse> call = ApiConfig.getApiService().profile(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ProfileResponse responses = response.body();
                        resultProfile.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultProfile.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                resultProfile.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultProfile;
    }

    public LiveData<ApiResponse<List<PetResponse>>> getPets() {
        MutableLiveData<ApiResponse<List<PetResponse>>> resultPets = new MutableLiveData<>();
        Call<PetsResponseParent> call = ApiConfig.getApiService().getPets();
        call.enqueue(new Callback<PetsResponseParent>() {
            @Override
            public void onResponse(Call<PetsResponseParent> call, Response<PetsResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        PetsResponseParent responses = response.body();
                        resultPets.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultPets.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<PetsResponseParent> call, Throwable t) {
                resultPets.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultPets;
    }

    public LiveData<ApiResponse<List<PetResponse>>> getMyPets(String token) {
        MutableLiveData<ApiResponse<List<PetResponse>>> resultPets = new MutableLiveData<>();
        Call<PetsResponseParent> call = ApiConfig.getApiService().getMyPets(token);
        call.enqueue(new Callback<PetsResponseParent>() {
            @Override
            public void onResponse(Call<PetsResponseParent> call, Response<PetsResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        PetsResponseParent responses = response.body();
                        resultPets.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultPets.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<PetsResponseParent> call, Throwable t) {
                resultPets.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultPets;
    }

    public LiveData<ApiResponse<PetResponse>> getPetDetail(long petId) {
        MutableLiveData<ApiResponse<PetResponse>> resultPet = new MutableLiveData<>();
        Call<PetDetailResponse> call = ApiConfig.getApiService().getPetDetail(petId);
        call.enqueue(new Callback<PetDetailResponse>() {
            @Override
            public void onResponse(Call<PetDetailResponse> call, Response<PetDetailResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        PetDetailResponse responses = response.body();
                        resultPet.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultPet.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<PetDetailResponse> call, Throwable t) {
                resultPet.setValue(ApiResponse.error(t.getMessage(), null));

            }
        });
        return resultPet;
    }

    public LiveData<ApiResponse<List<PetResponse>>> searchPets(String keyword) {
        MutableLiveData<ApiResponse<List<PetResponse>>> resultPets = new MutableLiveData<>();
        Call<PetsResponseParent> call = ApiConfig.getApiService().searchPets(keyword);
        call.enqueue(new Callback<PetsResponseParent>() {
            @Override
            public void onResponse(Call<PetsResponseParent> call, Response<PetsResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        PetsResponseParent responses = response.body();
                        resultPets.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultPets.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<PetsResponseParent> call, Throwable t) {
                resultPets.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultPets;
    }

    public LiveData<String> checkToken(String token) {
        MutableLiveData<String> resultToken = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().check(token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        SimpleResponse responses = response.body();
                        resultToken.setValue(response.message());
                    }
                } else {
                    resultToken.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultToken.setValue(t.getMessage());
            }
        });
        return resultToken;
    }

    public LiveData<String> updateProfile(String firstName, String lastName, String phoneNumber, String token) {
        MutableLiveData<String> resultProfile = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().profile(firstName, lastName, phoneNumber, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultProfile.setValue(response.message());
                    }
                } else {
                    resultProfile.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultProfile.setValue(t.getMessage());
            }
        });
        return resultProfile;
    }

    public LiveData<String> updateProfilePassword(String firstName, String lastName, String phoneNumber, String password, String token) {
        MutableLiveData<String> resultProfile = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().profileWithPassword(firstName, lastName, phoneNumber, password, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultProfile.setValue(response.message());
                    }
                } else {
                    resultProfile.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultProfile.setValue(t.getMessage());
            }
        });
        return resultProfile;
    }

    public MutableLiveData<ApiResponse<AlamatResponse>> getAlamat(String token) {
        MutableLiveData<ApiResponse<AlamatResponse>> resultAlamat = new MutableLiveData<>();
        Call<AlamatResponseParent> call = ApiConfig.getApiService().getMyAddress(token);
        call.enqueue(new Callback<AlamatResponseParent>() {
            @Override
            public void onResponse(Call<AlamatResponseParent> call, Response<AlamatResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        AlamatResponseParent responses = response.body();
                        resultAlamat.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultAlamat.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<AlamatResponseParent> call, Throwable t) {
                resultAlamat.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultAlamat;
    }

    public LiveData<String> sendAlamat(String alamat, long provinsiId, long kotaId, long kecamatanId, long kelurahanId, String token) {
        MutableLiveData<String> resultAlamat = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().sendAlamat(alamat, provinsiId, kotaId, kecamatanId, kelurahanId, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultAlamat.setValue(response.message());
                    }
                } else {
                    resultAlamat.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultAlamat.setValue(t.getMessage());
            }
        });
        return resultAlamat;
    }

    public LiveData<ApiResponse<AttachmentResponse>> uploadFile(File file, String token) {
        MutableLiveData<ApiResponse<AttachmentResponse>> resultAttachment = new MutableLiveData<>();
        RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
        Call<UploadResponse> call = ApiConfig.getApiService().uploadGambar(body, token);
        call.enqueue(new Callback<UploadResponse>() {
            @Override
            public void onResponse(Call<UploadResponse> call, Response<UploadResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        UploadResponse uploadResponse = response.body();
                        Log.i("ATTACHMENTs", response.body().getData().toString());
                        resultAttachment.setValue(ApiResponse.success(uploadResponse.getData()));
                    }
                } else {
                    resultAttachment.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<UploadResponse> call, Throwable t) {
                resultAttachment.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultAttachment;
    }

    public LiveData<ApiResponse<List<JenisResponse>>> getJenis() {
        MutableLiveData<ApiResponse<List<JenisResponse>>> resultJenis = new MutableLiveData<>();
        Call<JenisResponseParent> call = ApiConfig.getApiService().getJenis();
        call.enqueue(new Callback<JenisResponseParent>() {
            @Override
            public void onResponse(Call<JenisResponseParent> call, Response<JenisResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        JenisResponseParent responses = response.body();
                        resultJenis.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultJenis.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<JenisResponseParent> call, Throwable t) {
                resultJenis.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultJenis;
    }

    public LiveData<ApiResponse<List<RasResponse>>> getRas(long jenisId) {
        MutableLiveData<ApiResponse<List<RasResponse>>> resultRas = new MutableLiveData<>();
        Call<RasResponseParent> call = ApiConfig.getApiService().getRas(jenisId);
        call.enqueue(new Callback<RasResponseParent>() {
            @Override
            public void onResponse(Call<RasResponseParent> call, Response<RasResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        RasResponseParent responses = response.body();
                        resultRas.setValue(ApiResponse.success(responses.getData()));
                    }
                } else {
                    resultRas.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<RasResponseParent> call, Throwable t) {
                resultRas.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultRas;
    }

    public LiveData<String> uploadPet(long rasId, String namaHewan, int usia, int beratBadan, String kondisi, String jenisKelamin, int harga, String deskripsi, long attachmentId, String token) {
        MutableLiveData<String> resultPet = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().uploadPet(
                rasId, namaHewan, usia, beratBadan, kondisi, jenisKelamin, harga, deskripsi, attachmentId, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultPet.setValue(response.message());
                    }
                } else {
                    resultPet.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultPet.setValue(t.getMessage());
            }
        });
        return resultPet;
    }

    public LiveData<String> updatePet(long id, long rasId, String namaHewan, int usia, int beratBadan, String kondisi, String jenisKelamin, int harga, String deskripsi, long attachmentId, String token) {
        MutableLiveData<String> resultPet = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().updatePet(
                id, rasId, namaHewan, usia, beratBadan, kondisi, jenisKelamin, harga, deskripsi, attachmentId, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultPet.setValue(response.message());
                    }
                } else {
                    resultPet.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultPet.setValue(t.getMessage());
            }
        });
        return resultPet;
    }

    public LiveData<String> deletePet(long id, String token) {
        MutableLiveData<String> resultPet = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().deletePet(id, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultPet.setValue(response.message());
                    }
                } else {
                    resultPet.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultPet.setValue(t.getMessage());
            }
        });
        return resultPet;
    }

    public LiveData<String> adopt(long petId, String token) {
        MutableLiveData<String> resultAdopsi = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().adopsi(petId, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultAdopsi.setValue(response.message());
                    }
                } else {
                    resultAdopsi.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultAdopsi.setValue(t.getMessage());
            }
        });
        return resultAdopsi;
    }

    public LiveData<ApiResponse<AdopsiResponse>> getAdoptionDetail(long petId, String token) {
        MutableLiveData<ApiResponse<AdopsiResponse>> resultAdopsi = new MutableLiveData<>();
        Call<AdopsiDetailResponseParent> call = ApiConfig.getApiService().adopsiDetail(petId, token);
        call.enqueue(new Callback<AdopsiDetailResponseParent>() {
            @Override
            public void onResponse(Call<AdopsiDetailResponseParent> call, Response<AdopsiDetailResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultAdopsi.setValue(ApiResponse.success(response.body().getAdopsiResponse()));
                    }
                } else {
                    resultAdopsi.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<AdopsiDetailResponseParent> call, Throwable t) {
                resultAdopsi.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultAdopsi;
    }

    public LiveData<ApiResponse<AdopsiResponse>> getAdoptionOwner(long adopsiId, String token) {
        MutableLiveData<ApiResponse<AdopsiResponse>> resultAdopsi = new MutableLiveData<>();
        Call<AdopsiDetailResponseParent> call = ApiConfig.getApiService().adopsiDetailOwner(adopsiId, token);
        call.enqueue(new Callback<AdopsiDetailResponseParent>() {
            @Override
            public void onResponse(Call<AdopsiDetailResponseParent> call, Response<AdopsiDetailResponseParent> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultAdopsi.setValue(ApiResponse.success(response.body().getAdopsiResponse()));
                    }
                } else {
                    resultAdopsi.setValue(ApiResponse.error("onFailure: " + response.message(), null));
                }
            }

            @Override
            public void onFailure(Call<AdopsiDetailResponseParent> call, Throwable t) {
                resultAdopsi.setValue(ApiResponse.error(t.getMessage(), null));
            }
        });
        return resultAdopsi;
    }

    public LiveData<String> paymentConfirm(int hewanId, int fileId, String token) {
        MutableLiveData<String> resultPayment = new MutableLiveData<>();
        Call<SimpleResponse> call = ApiConfig.getApiService().uploadPayment(hewanId, fileId, token);
        call.enqueue(new Callback<SimpleResponse>() {
            @Override
            public void onResponse(Call<SimpleResponse> call, Response<SimpleResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        resultPayment.setValue(response.message());
                    }
                } else {
                    resultPayment.setValue(response.message());
                }
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t) {
                resultPayment.setValue(t.getMessage());
            }
        });
        return resultPayment;
    }
}