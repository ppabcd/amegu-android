package id.rezajuliandri.amegu.api;

import id.rezajuliandri.amegu.api.responses.KecamatanResponse;
import id.rezajuliandri.amegu.api.responses.KelurahanResponse;
import id.rezajuliandri.amegu.api.responses.KotaResponse;
import id.rezajuliandri.amegu.api.responses.LoginResponse;
import id.rezajuliandri.amegu.api.responses.ProfileResponse;
import id.rezajuliandri.amegu.api.responses.EmptyOkResponse;
import id.rezajuliandri.amegu.api.responses.ProvinsiResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

    @FormUrlEncoded
    @POST("v1/location/alamat")
    Call<EmptyOkResponse> storeAlamat(
            @Field("alamat") String alamat,
            @Field("kecamatanId") int kecamatanId,
            @Field("kotaId") int kotaId,
            @Field("provinsiId") int provinsiId
    );

    @GET("v1/location/provinsi")
    Call<ProvinsiResponse> getProvinsi();

    @GET("v1/location/{provinsi}/kota")
    Call<KotaResponse> getKota(@Path("provinsi") int provinsi);

    @GET("v1/location/{kota}/kecamatan")
    Call<KecamatanResponse> getKecamatan(@Path("kota") int kota);

    @GET("v1/location/{kecamatan}/kelurahan")
    Call<KelurahanResponse> getKelurahan(@Path("kecamatan") int kecamatan);

}
