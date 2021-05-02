package id.rezajuliandri.amegu.viewmodel;

import android.app.Application;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.database.UsersRepository;
import id.rezajuliandri.amegu.entity.Session;
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
     * @param onProfile Callback yang dipanggil ketika action sudah dilakukan
     */
    public void checkUserData(OnProfile onProfile){
        session.getUser(onProfile);
    }
}
