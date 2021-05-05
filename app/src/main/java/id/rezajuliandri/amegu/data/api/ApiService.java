package id.rezajuliandri.amegu.data.api;

import id.rezajuliandri.amegu.data.entity.responses.EmptyOkResponse;
import id.rezajuliandri.amegu.data.entity.responses.JenisResponse;
import id.rezajuliandri.amegu.data.entity.responses.KecamatanResponse;
import id.rezajuliandri.amegu.data.entity.responses.KelurahanResponse;
import id.rezajuliandri.amegu.data.entity.responses.KotaResponse;
import id.rezajuliandri.amegu.data.entity.responses.LoginResponse;
import id.rezajuliandri.amegu.data.entity.responses.PetResponse;
import id.rezajuliandri.amegu.data.entity.responses.ProfileResponse;
import id.rezajuliandri.amegu.data.entity.responses.ProvinsiResponse;
import id.rezajuliandri.amegu.data.entity.responses.RasResponse;
import id.rezajuliandri.amegu.data.entity.responses.UploadImageResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("v1/auth/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @GET("v1/auth/check")
    Call<EmptyOkResponse> check(@Query("token") String token);

    @GET("v1/auth/profile")
    Call<ProfileResponse> profile(@Query("token") String token);

    @FormUrlEncoded
    @POST("v1/auth/register")
    Call<EmptyOkResponse> register(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phoneNumber") String phoneNumber
    );

    @FormUrlEncoded
    @POST("v1/location/alamat")
    Call<EmptyOkResponse> sendAlamat(
            @Field("alamat") String alamat,
            @Field("kelurahanId") long kelurahanId,
            @Field("kecamatanId") long kecamatanId,
            @Field("kotaId") long kotaId,
            @Field("provinsiId") long provinsiId,
            @Field("token") String token
    );

    @GET("v1/location/provinsi")
    Call<ProvinsiResponse> getProvinsi();

    @GET("v1/location/{provinsi}/kota")
    Call<KotaResponse> getKota(@Path("provinsi") long provinsi);

    @GET("v1/location/{kota}/kecamatan")
    Call<KecamatanResponse> getKecamatan(@Path("kota") long kota);

    @GET("v1/location/{kecamatan}/kelurahan")
    Call<KelurahanResponse> getKelurahan(@Path("kecamatan") long kecamatan);

    @Multipart
    @POST("v1/main/image")
    Call<UploadImageResponse> postGambar(@Part MultipartBody.Part file,
                                         @Query("token") String token);

    @GET("/v1/main/jenis")
    Call<JenisResponse> getJenis();

    @GET("/v1/main/ras/{jenis}")
    Call<RasResponse> getRas(@Path("jenis") long jenis);

    @FormUrlEncoded
    @POST("/v1/main/hewan")
    Call<EmptyOkResponse> uploadPet(@Field("rasId") long rasId,
                                    @Field("namaHewan") String namaHewan,
                                    @Field("usia") int usia,
                                    @Field("beratBadan") int beratBadan,
                                    @Field("kondisi") String kondisi,
                                    @Field("jenisKelamin") String jenisKelamin,
                                    @Field("harga") int harga,
                                    @Field("deskripsi") String deskripsi,
                                    @Field("attachmentId") long attachmentId,
                                    @Field("token") String token
    );

    @PUT("/v1/main/hewan")
    Call<EmptyOkResponse> updatePet(@Field("id") long id,
                                    @Field("rasId") long rasId,
                                    @Field("namaHewan") String namaHewan,
                                    @Field("usia") int usia,
                                    @Field("beratBadan") int beratBadan,
                                    @Field("kondisi") String kondisi,
                                    @Field("jenisKelamin") String jenisKelamin,
                                    @Field("harga") int harga,
                                    @Field("deskripsi") String deskripsi,
                                    @Field("attachmentId") long attachmentId,
                                    @Field("token") String token
    );

    @DELETE("/v1/main/hewan")
    Call<EmptyOkResponse> deletePet(@Query("id") long id, @Query("token") String token);

    @GET("/v1/main/hewan")
    Call<PetResponse> getPet(@Query("token") String token);

    @GET("/v1/main/hewan/search/{query}")
    Call<PetResponse> searchPet(@Path("query") String query, @Query("token") String token);
}
