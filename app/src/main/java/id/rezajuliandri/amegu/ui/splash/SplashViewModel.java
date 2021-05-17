package id.rezajuliandri.amegu.ui.splash;

<<<<<<< HEAD
import android.app.Application;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.entity.auth.Session;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;

/**
 * Bagian yang menangani spash screen pada aplikasi
 */
public class SplashViewModel extends ViewModel {
    private final Session session;

    public SplashViewModel(Application application) {
        session = new Session(application);
    }

    /**
     * Proses pengecekan data user
     *
     * @param onProfile Callback yang dipanggil ketika action sudah dilakukan
     */
    public void checkUserData(OnProfile onProfile) {
        session.getUser(onProfile);
=======
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.AmeguRepository;
import id.rezajuliandri.amegu.data.local.entity.user.UserEntity;

public class SplashViewModel extends ViewModel {
    AmeguRepository ameguRepository;

    public SplashViewModel(AmeguRepository ameguRepository) {
        this.ameguRepository = ameguRepository;
    }

    public LiveData<UserEntity> checkUserLogin() {
        return ameguRepository.userRepository().getUser();
>>>>>>> v2
    }
}
