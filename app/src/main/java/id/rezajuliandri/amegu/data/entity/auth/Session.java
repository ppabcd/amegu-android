package id.rezajuliandri.amegu.data.entity.auth;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import id.rezajuliandri.amegu.data.api.ApiConfig;
import id.rezajuliandri.amegu.data.entity.location.Alamat;
import id.rezajuliandri.amegu.data.entity.responses.ProfileResponse;
import id.rezajuliandri.amegu.data.database.AlamatRepository;
import id.rezajuliandri.amegu.data.database.UsersRepository;
import id.rezajuliandri.amegu.interfaces.auth.OnAlamat;
import id.rezajuliandri.amegu.interfaces.auth.OnLogout;
import id.rezajuliandri.amegu.interfaces.auth.OnProfile;
import id.rezajuliandri.amegu.interfaces.auth.OnToken;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class Session untuk memanipulasi data user dari server maupun dari database
 */
public class Session extends ViewModel {
    Application application;
    UsersRepository usersRepository;
    AlamatRepository alamatRepository;

    public Session(Application application) {
        this.application = application;
        usersRepository = new UsersRepository(application);
        alamatRepository = new AlamatRepository(application);
    }

    /**
     * Mengambil data user dari database
     *
     * @param onProfile
     */
    public void getUser(OnProfile onProfile) {
        usersRepository.getUserData(new OnProfile() {
            @Override
            public void success(Users users) {
                onProfile.success(users);
            }

            @Override
            public void error(String message) {
                onProfile.error(message);
            }
        });
    }

    public void getAlamat(OnAlamat onAlamat) {
        alamatRepository.getAlamat(new OnAlamat() {
            @Override
            public void success(Alamat alamat) {
                onAlamat.success(alamat);
            }

            @Override
            public void error(String error) {
                onAlamat.error(error);
            }
        });
    }

    /**
     * Memasukkan data user kedalam database
     *
     * @param onProfile
     * @param users
     */
    public void setProfile(OnProfile onProfile, Users users) {
        try {
            usersRepository.insert(users);
            onProfile.success(users);
        } catch (Exception exception) {
            onProfile.error(exception.getMessage());
        }
    }

    /**
     * Memasukkan data user kedalam database
     *
     * @param users
     */
    public void setProfile(Users users) {
        try {
            usersRepository.insert(users);
            if (users.getAlamat() != null) {
                alamatRepository.insert(users.getAlamat());
            }
        } catch (Exception exception) {
            Log.e(Session.class.getName(), "setProfile: " + exception.getMessage());
        }
    }

    /**
     * Mengambil data token dari database lokal
     *
     * @param onToken
     */
    public void getToken(OnToken onToken) {
        usersRepository.getUserData(new OnProfile() {
            @Override
            public void success(Users users) {
                onToken.success(users.getToken());
            }

            @Override
            public void error(String message) {
                onToken.error(message);
            }
        });
    }

    public void setToken(String token) {
        Users users = new Users();
        users.setToken(token);
        usersRepository.insert(users);
    }

    /**
     * Melakukan refresh data user
     *
     * @param onProfile
     */
    public void refreshUserData(OnProfile onProfile, String token) {
        try {
            Call<ProfileResponse> profileResponseCall = ApiConfig.getApiService().profile(token);
            profileResponseCall.enqueue(new Callback<ProfileResponse>() {
                @Override
                public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            ProfileResponse profileResponse = response.body();
                            Users users = profileResponse.getData();
                            users.setToken(token);
                            setProfile(users);
                            onProfile.success(users);
                        }
                    } else {
                        onProfile.error("onFailure:" + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ProfileResponse> call, Throwable t) {
                    onProfile.error(t.getMessage());
                }
            });
        } catch (Exception e) {
            onProfile.error(e.getMessage());
        }
    }

    public void refreshUserData(OnProfile onProfile) {
        try {
            this.getToken(new OnToken() {
                @Override
                public void success(String token) {
                    Call<ProfileResponse> profileResponseCall = ApiConfig.getApiService().profile(token);
                    profileResponseCall.enqueue(new Callback<ProfileResponse>() {
                        @Override
                        public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                            if (response.isSuccessful()) {
                                if (response.body() != null) {
                                    ProfileResponse profileResponse = response.body();
                                    Users users = profileResponse.getData();
                                    users.setToken(token);
                                    setProfile(users);
                                    onProfile.success(users);
                                }
                            } else {
                                onProfile.error("onFailure:" + response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<ProfileResponse> call, Throwable t) {
                            onProfile.error(t.getMessage());
                        }
                    });
                }

                @Override
                public void error(String error) {
                    onProfile.error(error);
                }
            });
        } catch (Exception e) {
            onProfile.error(e.getMessage());
        }
    }

    /**
     * Proses logout dari sistem
     *
     * @param onLogout
     */
    public final void logout(OnLogout onLogout) {
        try {
            usersRepository.delete();
            alamatRepository.delete();
            onLogout.success();
        } catch (Exception exception) {
            onLogout.error(exception.getMessage());
        }
    }
}
