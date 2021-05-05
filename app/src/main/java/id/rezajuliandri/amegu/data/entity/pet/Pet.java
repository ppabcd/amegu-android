package id.rezajuliandri.amegu.data.entity.pet;

import android.os.Parcel;
import android.os.Parcelable;

import id.rezajuliandri.amegu.data.entity.auth.Users;

public class Pet implements Parcelable {
    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };
    private int usia;
    private String kondisi;
    private int rasId;
    private Ras ras;
    private int userId;
    private Users user;
    private String namaHewan;
    private int beratBadan;
    private String createdAt;
    private int harga;
    private Attachment attachment;
    private String jenisKelamin;
    private int id;
    private int attachmentId;
    private String deskripsi;
    private String updatedAt;

    protected Pet(Parcel in) {
        usia = in.readInt();
        kondisi = in.readString();
        rasId = in.readInt();
        ras = in.readParcelable(Ras.class.getClassLoader());
        userId = in.readInt();
        user = in.readParcelable(Users.class.getClassLoader());
        namaHewan = in.readString();
        beratBadan = in.readInt();
        createdAt = in.readString();
        harga = in.readInt();
        attachment = in.readParcelable(Attachment.class.getClassLoader());
        jenisKelamin = in.readString();
        id = in.readInt();
        attachmentId = in.readInt();
        deskripsi = in.readString();
        updatedAt = in.readString();
    }

    public int getUsia() {
        return usia;
    }

    public void setUsia(int usia) {
        this.usia = usia;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public int getRasId() {
        return rasId;
    }

    public void setRasId(int rasId) {
        this.rasId = rasId;
    }

    public Ras getRas() {
        return this.ras;
    }

    public void setRas(Ras ras) {
        this.ras = ras;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Users getUser() {
        return this.user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getNamaHewan() {
        return namaHewan;
    }

    public void setNamaHewan(String namaHewan) {
        this.namaHewan = namaHewan;
    }

    public int getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(int beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return
                "DataItem{" +
                        "usia = '" + usia + '\'' +
                        ",kondisi = '" + kondisi + '\'' +
                        ",rasId = '" + rasId + '\'' +
                        ",userId = '" + userId + '\'' +
                        ",namaHewan = '" + namaHewan + '\'' +
                        ",beratBadan = '" + beratBadan + '\'' +
                        ",createdAt = '" + createdAt + '\'' +
                        ",harga = '" + harga + '\'' +
                        ",attachment = '" + attachment + '\'' +
                        ",jenisKelamin = '" + jenisKelamin + '\'' +
                        ",id = '" + id + '\'' +
                        ",attachmentId = '" + attachmentId + '\'' +
                        ",deskripsi = '" + deskripsi + '\'' +
                        ",updatedAt = '" + updatedAt + '\'' +
                        "}";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(usia);
        dest.writeString(kondisi);
        dest.writeInt(rasId);
        dest.writeInt(userId);
        dest.writeString(namaHewan);
        dest.writeInt(beratBadan);
        dest.writeString(createdAt);
        dest.writeInt(harga);
        dest.writeParcelable(attachment, flags);
        dest.writeString(jenisKelamin);
        dest.writeInt(id);
        dest.writeInt(attachmentId);
        dest.writeString(deskripsi);
        dest.writeString(updatedAt);
    }
}
