package id.rezajuliandri.amegu.ui.favorite;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import id.rezajuliandri.amegu.data.api.ApiConfig;
import id.rezajuliandri.amegu.data.database.FavoriteRepository;
import id.rezajuliandri.amegu.data.entity.pet.Favorite;
import id.rezajuliandri.amegu.data.entity.pet.Pet;
import id.rezajuliandri.amegu.data.entity.responses.PetDetailResponse;
import id.rezajuliandri.amegu.interfaces.pet.OnAllFavorites;
import id.rezajuliandri.amegu.interfaces.pet.OnGetPetDetail;
import id.rezajuliandri.amegu.interfaces.pet.OnPet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteViewModel extends ViewModel {

    private Application mApplication;
    FavoriteRepository favoriteRepository;

    public FavoriteViewModel(Application application) {
        mApplication = application;
        favoriteRepository = new FavoriteRepository(mApplication);
    }

    public void getFavorites(OnPet onPet, String token) {
        favoriteRepository.getAllFavorite(new OnAllFavorites() {
            @Override
            public void success(List<Favorite> favoriteList) {
                List<Pet> pets = new ArrayList<>();
                Log.i("FAVORITE", favoriteList.toString());
                final int[] total = {favoriteList.size()};
                for (final int[] i = {0}; i[0] < favoriteList.size(); i[0]++) {
                    int finalI = i[0];
                    getPetDetailById(new OnGetPetDetail() {
                        @Override
                        public void success(Pet pet) {
                            Log.i("PET_ITEM", pet.toString());
                            pets.add(pet);
                            if(finalI == total[0] -1){
                                onPet.success(pets);
                            }
                        }

                        @Override
                        public void error(String message) {
                            total[0]--;
                            Log.e("FavoriteViewModel@getFavorite", message);
                        }
                    }, favoriteList.get(i[0]).getPetId(), token);
                    Log.i("FAVORITE_ITEM", favoriteList.get(i[0]).toString());
                }
            }

            @Override
            public void error(String message) {
                onPet.error(message);
            }
        });
    }

    private void getPetDetailById(OnGetPetDetail onGetPetDetail, long id, String token) {
        Call<PetDetailResponse> petDetailResponseCall = ApiConfig.getApiService().detailPet(id, token);
        petDetailResponseCall.enqueue(new Callback<PetDetailResponse>() {
            @Override
            public void onResponse(Call<PetDetailResponse> call, Response<PetDetailResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        PetDetailResponse petDetailResponse = response.body();
                        Pet pet = petDetailResponse.getData();
                        onGetPetDetail.success(pet);
                    }
                } else {
                    onGetPetDetail.error("onFailure:" + response.message());
                }
            }

            @Override
            public void onFailure(Call<PetDetailResponse> call, Throwable t) {
                Toast.makeText(mApplication, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("FavoriteViewModel@getPetDetailById", t.getMessage());
            }
        });
    }
}
