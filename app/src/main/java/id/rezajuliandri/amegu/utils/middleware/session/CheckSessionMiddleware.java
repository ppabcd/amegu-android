package id.rezajuliandri.amegu.utils.middleware.session;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import id.rezajuliandri.amegu.ui.auth.login.LoginActivity;
import id.rezajuliandri.amegu.utils.NetworkUtils;
import id.rezajuliandri.amegu.viewmodel.ViewModelFactory;

/**
 * Bagian yang mengatur apakah data user tersedia di server
 */
public class CheckSessionMiddleware {
    private static CheckSessionMiddleware INSTANCE = null;
    private final Context mContext;
    CheckSessionViewModel viewModel;

    public CheckSessionMiddleware(Context context) {
        mContext = context;
    }

    public static CheckSessionMiddleware getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CheckSessionMiddleware(context);
        }
        return INSTANCE;
    }

    public void checkValidToken() {
        setViewModel();
        if (NetworkUtils.isConnectedFast(mContext)) {
            viewModel.getUser().observe((AppCompatActivity) mContext, userEntity -> {
                if (userEntity != null) {
                    viewModel.checkToken(userEntity.getToken()).observe((AppCompatActivity) mContext, message -> {
                        if (message.toLowerCase().equals("ok")) {
                            return;
                        }
                        viewModel.logout().observe((AppCompatActivity) mContext, status -> {
                            if (status != null) {
                                Intent intent = new Intent(mContext.getApplicationContext(), LoginActivity.class);
                                mContext.startActivity(intent);
                                ((AppCompatActivity) mContext).finish();
                            }
                        });
                    });
                }
            });
        }
    }

    private void setViewModel() {
        ViewModelFactory viewModelFactory = ViewModelFactory.getInstance(mContext);
        viewModel = new ViewModelProvider((ViewModelStoreOwner) mContext, viewModelFactory).get(CheckSessionViewModel.class);
    }
}
