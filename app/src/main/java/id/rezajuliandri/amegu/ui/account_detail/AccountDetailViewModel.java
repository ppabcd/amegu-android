package id.rezajuliandri.amegu.ui.account_detail;

import android.app.Application;

import androidx.lifecycle.ViewModel;

public class AccountDetailViewModel extends ViewModel {
    private Application mApplication;

    public AccountDetailViewModel(Application application){
        this.mApplication = application;
    }

}
