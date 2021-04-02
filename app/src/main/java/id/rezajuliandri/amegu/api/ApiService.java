package id.rezajuliandri.amegu.api;

import id.rezajuliandri.amegu.api.responses.LoginResponse;
import id.rezajuliandri.amegu.api.responses.ProfileResponse;
import id.rezajuliandri.amegu.api.responses.EmptyOkResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("v1/auth/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("v1/auth/profile")
    Call<ProfileResponse> profile(@Query("token") String token);

    @FormUrlEncoded
    @POST("v1/auth/register")
    Call<EmptyOkResponse> register(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("email") String email,
            @Field("password") String password
    );
}
