package id.rezajuliandri.amegu.ui.middleware;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import id.rezajuliandri.amegu.ui.auth.LoginActivity;

public class BaseActivity extends AppCompatActivity {
    CheckSessionMiddleware checkSessionMiddleware;

    protected boolean shouldCheckValidToken = true;

    public void changeValidateToken(boolean value){
        shouldCheckValidToken = value;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.checkActivity();
    }

    private void checkActivity(){
        checkSessionMiddleware = new CheckSessionMiddleware(this);
        if(!checkSessionMiddleware.checkConnection()){
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Connection disconnected");
            dialog.setMessage("Please check your connection.");
            dialog.setPositiveButton("Ok", (dialog1, which) -> {

            });
            dialog.show();
        } else {
            if(shouldCheckValidToken)
                checkSessionMiddleware.checkValidToken();
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
