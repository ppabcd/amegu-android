package id.rezajuliandri.amegu.data.remote.response.pet.pets;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.attachment.upload.AttachmentResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.profile.UserProfileResponse;
import id.rezajuliandri.amegu.data.remote.response.pet.ras.RasResponse;

public class PetResponse {

    @SerializedName("usia")
    private int usia;

    @SerializedName("kondisi")
    private String kondisi;

    @SerializedName("ras")
    private RasResponse ras;

    @SerializedName("rasId")
    private int rasId;

    @SerializedName("userId")
    private int userId;

    @SerializedName("namaHewan")
    private String namaHewan;

    @SerializedName("beratBadan")
    private int beratBadan;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("harga")
    private int harga;

    @SerializedName("attachment")
    private AttachmentResponse attachment;

    @SerializedName("jenisKelamin")
    private String jenisKelamin;

    @SerializedName("id")
    private int id;

    @SerializedName("attachmentId")
    private int attachmentId;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("user")
    private UserProfileResponse user;

    @SerializedName("updatedAt")
    private String updatedAt;

    public int getUsia() {
        return usia;
    }

    public String getKondisi() {
        return kondisi;
    }

    public RasResponse getRas() {
        return ras;
    }

    public int getRasId() {
        return rasId;
    }

    public int getUserId() {
        return userId;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public int getHarga() {
        return harga;
    }

    public AttachmentResponse getAttachment() {
        return attachment;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public int getId() {
        return id;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public UserProfileResponse getUser() {
        return user;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}