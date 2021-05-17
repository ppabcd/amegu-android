package id.rezajuliandri.amegu.data.remote.api;

import id.rezajuliandri.amegu.data.remote.response.SimpleResponse;
import id.rezajuliandri.amegu.data.remote.response.attachment.upload.UploadResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.login.LoginResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.ProfileResponse;
import id.rezajuliandri.amegu.data.remote.response.location.alamat.AlamatResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.kecamatan.KecamatanResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.kelurahan.KelurahanResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.kota.KotaResponseParent;
import id.rezajuliandri.amegu.data.remote.response.location.provinsi.ProvinsiResponseParent;
import id.rezajuliandri.amegu.data.remote.response.pet.detail.PetDetailResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.jenis.JenisResponseParent;
import id.rezajuliandri.amegu.data.remote.response.pet.pets.PetsResponseParent;
import id.rezajuliandri.amegu.data.remote.response.pet.ras.RasResponseParent;
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
    @GET("v1/location/provinsi")
    Call<ProvinsiResponseParent> getProvinsi();

    @GET("v1/location/{provinsi}/kota")
    Call<KotaResponseParent> getKota(@Path("provinsi") long provinsi);

    @GET("v1/location/{kota}/kecamatan")
    Call<KecamatanResponseParent> getKecamatan(@Path("kota") long kota);

    @GET("v1/location/{kecamatan}/kelurahan")
    Call<KelurahanResponseParent> getKelurahan(@Path("kecamatan") long kecamatan);

    @FormUrlEncoded
    @POST("v1/auth/login")
    Call<LoginResponse> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("v1/auth/register")
    Call<SimpleResponse> register(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("email") String email,
            @Field("password") String password,
            @Field("phoneNumber") String phoneNumber
    );

    @GET("v1/auth/check")
    Call<SimpleResponse> check(@Query("token") String token);

    @GET("v1/auth/profile")
    Call<ProfileResponse> profile(@Query("token") String token);

    @FormUrlEncoded
    @POST("v1/auth/profile")
    Call<SimpleResponse> profile(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("phoneNumber") String phoneNumber,
            @Field("token") String token
    );

    @FormUrlEncoded
    @POST("v1/auth/profile")
    Call<SimpleResponse> profileWithPassword(
            @Field("firstName") String firstName,
            @Field("lastName") String lastName,
            @Field("phoneNumber") String phoneNumber,
            @Field("password") String password,
            @Field("token") String token
    );


    @FormUrlEncoded
    @POST("/v1/main/hewan")
    Call<SimpleResponse> uploadPet(@Field("rasId") long rasId,
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
    Call<SimpleResponse> updatePet(@Field("id") long id,
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
    Call<SimpleResponse> deletePet(@Query("id") long id, @Query("token") String token);

    @GET("/v1/main/hewan")
    Call<PetsResponseParent> getPets();

    @GET("/v1/main/hewan/{id}")
    Call<PetDetailResponse> getPetDetail(@Path("id") long id);

    @GET("/v1/main/hewan/search/{query}")
    Call<PetsResponseParent> searchPets(@Path("query") String query);

    @GET("/v1/main/hewan/my")
    Call<PetsResponseParent> getMyPets(@Query("token") String token);

    @GET("/v1/location/alamat")
    Call<AlamatResponseParent> getMyAddress(@Query("token") String token);

    @FormUrlEncoded
    @POST("v1/location/alamat")
    Call<SimpleResponse> sendAlamat(
            @Field("alamat") String alamat,
            @Field("provinsiId") long provinsiId,
            @Field("kotaId") long kotaId,
            @Field("kecamatanId") long kecamatanId,
            @Field("kelurahanId") long kelurahanId,
            @Field("token") String token
    );

    @Multipart
    @POST("v1/main/image")
    Call<UploadResponse> uploadGambar(@Part MultipartBody.Part file,
                                      @Query("token") String token);

    @GET("/v1/main/jenis")
    Call<JenisResponseParent> getJenis();

    @GET("/v1/main/ras/{jenis}")
    Call<RasResponseParent> getRas(@Path("jenis") long jenis);
}
