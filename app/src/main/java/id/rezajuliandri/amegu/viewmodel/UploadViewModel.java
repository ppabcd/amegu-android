package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.io.File;
import java.util.ArrayList;

import id.rezajuliandri.amegu.api.ApiConfig;
import id.rezajuliandri.amegu.api.responses.EmptyOkResponse;
import id.rezajuliandri.amegu.api.responses.JenisResponse;
import id.rezajuliandri.amegu.api.responses.RasResponse;
import id.rezajuliandri.amegu.api.responses.UploadImageResponse;
import id.rezajuliandri.amegu.api.responses.data.pet.Attachment;
import id.rezajuliandri.amegu.api.responses.data.pet.Jenis;
import id.rezajuliandri.amegu.api.responses.data.pet.Ras;
import id.rezajuliandri.amegu.interfaces.OnImageUploaded;
import id.rezajuliandri.amegu.interfaces.pet.OnJenis;
import id.rezajuliandri.amegu.interfaces.pet.OnPetUploaded;
import id.rezajuliandri.amegu.interfaces.pet.OnRas;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadViewModel extends ViewModel {
    Application mApplication;

    public UploadViewModel(Application application) {
        mApplication = application;
    }

    // Todo Upload file
    public void postImage(File file, String token, OnImageUploaded onImageUploaded) {
        try {
            RequestBody requestFile = RequestBody.create(file, MediaType.parse("multipart/form-data"));
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
            Call<UploadImageResponse> uploadImageResponseCall = ApiConfig.getApiService().postGambar(body, token);
            uploadImageResponseCall.enqueue(new Callback<UploadImageResponse>() {
                @Override
                public void onResponse(Call<UploadImageResponse> call, Response<UploadImageResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            Attachment dataImage = response.body().getDataImage();
                            onImageUploaded.success(dataImage);
                        }
                    } else {
                        onImageUploaded.error(response.message());
                    }
                }

                @Override
                public void onFailure(Call<UploadImageResponse> call, Throwable t) {
                    onImageUploaded.error(t.getMessage());
                }
            });
        } catch (Exception e) {
            onImageUploaded.error(e.getMessage());
            Log.e("MainViewmodel", e.getMessage());
        }
    }

    public void getJenis(OnJenis onJenis) {
        try {
            Call<JenisResponse> jenisResponseCall = ApiConfig.getApiService().getJenis();
            jenisResponseCall.enqueue(new Callback<JenisResponse>() {
                @Override
                public void onResponse(Call<JenisResponse> call, Response<JenisResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            ArrayList<Jenis> jenis = response.body().getData();
                            onJenis.success(jenis);
                        }
                    }
                }

                @Override
                public void onFailure(Call<JenisResponse> call, Throwable t) {
                    onJenis.error(t.getMessage());
                }
            });
        } catch (Exception e) {
            onJenis.error(e.getMessage());
            Log.e("MainViewModel", e.getMessage());
        }
    }

    public void getRas(long jenis, OnRas onRas) {
        try {
            Call<RasResponse> rasResponseCall = ApiConfig.getApiService().getRas(jenis);
            rasResponseCall.enqueue(new Callback<RasResponse>() {
                @Override
                public void onResponse(Call<RasResponse> call, Response<RasResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            ArrayList<Ras> ras = response.body().getData();
                            onRas.success(ras);
                        }
                    }
                }

                @Override
                public void onFailure(Call<RasResponse> call, Throwable t) {
                    onRas.error(t.getMessage());
                }
            });
        } catch (Exception e) {
            onRas.error(e.getMessage());
            Log.e("MainViewModel", e.getMessage());
        }
    }

    /**
     *
     * @param rasId
     * @param namaHewan
     * @param usia
     * @param beratBadan
     * @param kondisi
     * @param jenisKelamin
     * @param harga
     * @param deskripsi
     * @param attachmentId
     * @param onPetUploaded
     */
    public void uploadPet(long rasId,
                          String namaHewan,
                          int usia,
                          int beratBadan,
                          String kondisi,
                          String jenisKelamin,
                          int harga,
                          String deskripsi,
                          long attachmentId,
                          String token,
                          OnPetUploaded onPetUploaded) {
        Call<EmptyOkResponse> uploadPetCall = ApiConfig.getApiService().uploadPet(
                rasId,
                namaHewan,
                usia,
                beratBadan,
                kondisi,
                jenisKelamin,
                harga,
                deskripsi,
                attachmentId,
                token
        );
        uploadPetCall.enqueue(new Callback<EmptyOkResponse>() {
            @Override
            public void onResponse(Call<EmptyOkResponse> call, Response<EmptyOkResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        onPetUploaded.success();
                    }
                } else {
                    onPetUploaded.error(response.message());
                }
            }

            @Override
            public void onFailure(Call<EmptyOkResponse> call, Throwable t) {
                onPetUploaded.error(t.getMessage());
            }
        });
    }
}
