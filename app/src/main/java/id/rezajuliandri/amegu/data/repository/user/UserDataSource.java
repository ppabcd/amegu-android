package id.rezajuliandri.amegu.data.repository.user;

import androidx.lifecycle.LiveData;

import id.rezajuliandri.amegu.data.local.entity.location.AlamatEntity;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;
import id.rezajuliandri.amegu.vo.Resource;

/**
 * Ringkasan UserDataSource
 */
public interface UserDataSource {
    /**
     * Mengambil data user dari database
     * @return LiveData<UserEntity>
     */
    LiveData<UserEntity> getUser();

    /**
     * Mengirimkan data login ke API dan menerima token dari API
     * @param username Username user untuk login
     * @param password Password user untuk login
     * @return LiveData<Resource<UserEntity>>>
     */
    LiveData<Resource<UserEntity>> loginUser(String username, String password);

    /**
     * Mengambil data profile dari token yang didapatkan ketika login
     * @param token toke user untuk data profile
     * @return LiveData<Resource<UserEntity>>>
     */
    LiveData<Resource<UserEntity>> getProfile(String token);

    /**
     * Mengupdate profile jika ada perubahan
     * @param token Token user untuk mendapatkan data user
     * @return LiveData<Resource<UserEntity>>>
     */
    LiveData<Resource<UserEntity>> updateProfile(String token);

    /**
     * Melakukan pengecekan apakah token yang ada valid atau tidak
     * @param token Token user untuk melakukan pemeriksaan token
     * @return LiveData<String>
     */
    LiveData<String> checkToken(String token);

    /**
     * Melakukan logout dengan menghapus data user dari database
     * @return LiveData<String>
     */
    LiveData<String> logout();

    /**
     * Mengambil alamat user dari database lokal
     * @param alamatId Id pada alamat untuk diambil dari database
     * @return LiveData<AlamatEntity>
     */
    LiveData<AlamatEntity> getAlamat(int alamatId);

    /**
     * Mengupdate profile ke API
     * @param firstName Nama depan user untuk diupdate
     * @param lastName Nama belakang user untuk diupdate
     * @param phoneNumber Nomor telepon user untuk diupdate
     * @param token Token user untuk di update
     * @return LiveData<String>
     */
    LiveData<String> updateProfile(String firstName, String lastName, String phoneNumber, String token);

    /**
     * Mengupdate profile dengan tambahan password
     * @param firstName Nama depan user untuk diupdate
     * @param lastName Nama belakang user untuk diupdate
     * @param phoneNumber Nomor telepon user untuk diupdate
     * @param password Password baru untuk diupdate
     * @param token Token user untuk diupdate
     * @return LiveData<String>
     */
    LiveData<String> updateProfilePassword(String firstName, String lastName, String phoneNumber, String password, String token);

    /**
     * Melakukan registrasi user baru
     * @param firstname Nama depan user untuk registrasi
     * @param lastName Nama belakang user untuk registrasi
     * @param phoneNumber Nomor telepon user untuk registrasi
     * @param password Password baru untuk registrasi
     * @return LiveData<String>
     */
    LiveData<String> register(String firstname, String lastName, String email, String password, String phoneNumber);
}
