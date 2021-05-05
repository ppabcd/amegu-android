package id.rezajuliandri.amegu.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.api.ApiConfig;
import id.rezajuliandri.amegu.data.entity.responses.PetResponse;
import id.rezajuliandri.amegu.interfaces.pet.OnPet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    Application mApplication;

    public HomeViewModel(Application application) {
        mApplication = application;
    }

    public void getPet(String token, OnPet onPet) {
        try {
            Call<PetResponse> petResponseCall = ApiConfig.getApiService().getPet(token);
            petResponseCall.enqueue(new Callback<PetResponse>() {
                @Override
                public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            PetResponse petResponse = response.body();
                            onPet.success(petResponse.getData());
                            onPet.successWithPagination(petResponse.getData(), petResponse.getPagination());
                        }
                    } else {
                        onPet.error("onFailure:" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<PetResponse> call, Throwable t) {
                    onPet.error(t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("HomeViewmodel@getPet", e.getMessage());
            onPet.error(e.getMessage());
        }
    }

    public void searchPet(String query, String token, OnPet onPet) {
        try {
            Call<PetResponse> petResponseCall = ApiConfig.getApiService().searchPet(query, token);
            petResponseCall.enqueue(new Callback<PetResponse>() {
                @Override
                public void onResponse(Call<PetResponse> call, Response<PetResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            PetResponse petResponse = response.body();
                            onPet.success(petResponse.getData());
                            onPet.successWithPagination(petResponse.getData(), petResponse.getPagination());
                        }
                    } else {
                        onPet.error("onFailure:" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<PetResponse> call, Throwable t) {
                    onPet.error(t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.e("HomeViewmodel@searchPet", e.getMessage());
            onPet.error(e.getMessage());
        }
    }
}
