package id.rezajuliandri.amegu.ui.favorite;

import android.app.Application;

import androidx.lifecycle.ViewModel;

public class FavoriteViewModel  extends ViewModel {

    private Application mApplication;
    public FavoriteViewModel(Application application){
        mApplication = application;
    }
}
