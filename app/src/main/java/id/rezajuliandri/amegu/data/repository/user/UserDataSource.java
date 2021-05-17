package id.rezajuliandri.amegu.data.repository.user;

import androidx.lifecycle.LiveData;

import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

/**
 * Ringkasan UserDataSource
 */
public interface UserDataSource {
    LiveData<UserEntity> getUser();

    LiveData<Resource<UserEntity>> loginUser(String username, String password);

    LiveData<Resource<UserEntity>> getProfile(String token);

    LiveData<Resource<UserEntity>> updateProfile(String token);

    LiveData<String> checkToken(String token);

    LiveData<String> logout();

    LiveData<AlamatEntity> getAlamat(int alamatId);

    LiveData<String> updateProfile(String firstName, String lastName, String phoneNumber, String token);

    LiveData<String> updateProfilePassword(String firstName, String lastName, String phoneNumber, String password, String token);

    LiveData<String> register(String firstname, String lastName, String email, String password, String phoneNumber);
}
