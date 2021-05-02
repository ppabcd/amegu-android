package id.rezajuliandri.amegu.ui.middleware;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.rezajuliandri.amegu.api.ApiConfig;
import id.rezajuliandri.amegu.api.responses.EmptyOkResponse;
import id.rezajuliandri.amegu.entity.Session;
import id.rezajuliandri.amegu.interfaces.auth.OnLogout;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import id.rezajuliandri.amegu.ui.auth.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckSessionMiddleware {
    AppCompatActivity appCompatActivity;
    Session session;

    CheckSessionMiddleware(AppCompatActivity activity) {
        appCompatActivity = activity;
        session = new Session(activity.getApplication());
    }

    public boolean checkConnection() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) appCompatActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        return connected;
    }

    public void checkValidToken(){
        try{
            session.getToken(new OnToken() {
                @Override
                public void success(String token) {
                    Call<EmptyOkResponse> emptyOkResponseCall = ApiConfig.getApiService().check(token);
                    emptyOkResponseCall.enqueue(new Callback<EmptyOkResponse>() {
                        @Override
                        public void onResponse(Call<EmptyOkResponse> call, Response<EmptyOkResponse> response) {
                            if (!response.isSuccessful()) {
                                Toast.makeText(appCompatActivity, response.message(), Toast.LENGTH_SHORT).show();
                                logoutApplication();
                            }
                        }

                        @Override
                        public void onFailure(Call<EmptyOkResponse> call, Throwable t) {
                            Log.e("onFailure:CheckSessionMiddleware", t.getMessage());
                            logoutApplication();
                        }
                    });
                }

                @Override
                public void error(String error) {
                    Log.e("error:CheckSessionMiddleware", error);
                    logoutApplication();
                }
            });
        } catch (Exception e){
            Log.e("ExceptionCheckSessionMiddleware", e.getMessage());
        }
    }
    private void logoutApplication(){
        session.logout(new OnLogout() {
            @Override
            public void success() {
                Toast.makeText(appCompatActivity, "Sesi login berakhir, silahkan login kembali", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(appCompatActivity, LoginActivity.class);
                appCompatActivity.startActivity(intent);
            }

            @Override
            public void error(String message) {
                Toast.makeText(appCompatActivity, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
