package id.rezajuliandri.amegu.utils;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import id.rezajuliandri.amegu.utils.middleware.session.CheckSessionMiddleware;

public class BaseActivity extends AppCompatActivity {
    protected boolean shouldCheckValidToken = true;

    public void changeValidateToken(boolean value) {
        shouldCheckValidToken = value;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.checkActivity();
    }

    private void checkActivity() {
        if (!NetworkUtils.isConnectedFast(this)) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Connection disconnected");
            dialog.setMessage("Please check your connection.");
            dialog.setPositiveButton("Ok", (dialog1, which) -> {

            });
            dialog.show();
        } else {
            if (shouldCheckValidToken)
                CheckSessionMiddleware.getInstance(this).checkValidToken();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.checkActivity();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.checkActivity();
    }
}
