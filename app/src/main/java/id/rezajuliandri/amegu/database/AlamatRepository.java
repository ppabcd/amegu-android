package id.rezajuliandri.amegu.database;

import android.app.Application;

import id.rezajuliandri.amegu.entity.Alamat;
import id.rezajuliandri.amegu.interfaces.auth.OnAlamat;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;

public class AlamatRepository {
    AlamatDao mAlamatDao;
    public AlamatRepository(Application application) {
        AmeguDatabase ameguDatabase = AmeguDatabase.getDatabase(application);
        mAlamatDao = ameguDatabase.alamatDao();
    }
    public void insert(Alamat alamat){
        alamat.setId(1);
        AmeguDatabase.databaseWriteExecutor.execute(()-> mAlamatDao.insert(alamat));
    }
    public void getAlamat(OnAlamat onAlamat) {
        AmeguDatabase.databaseWriteExecutor.execute(() -> onAlamat.success(mAlamatDao.getAlamat()));
    }
    public void delete(){
        AmeguDatabase.databaseWriteExecutor.execute(mAlamatDao::delete);
    }
}
