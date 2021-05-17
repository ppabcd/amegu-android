package id.rezajuliandri.amegu.data.local.entity.pet;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class PetEntity implements Parcelable {
    public static final Creator<PetEntity> CREATOR = new Creator<PetEntity>() {
        @Override
        public PetEntity createFromParcel(Parcel in) {
            return new PetEntity(in);
        }

        @Override
        public PetEntity[] newArray(int size) {
            return new PetEntity[size];
        }
    };
    @PrimaryKey
    @ColumnInfo(name = "_id")
    private final long id;
    private final int usia;
    private final String kondisi;
    private final int rasId;
    private final String ras;
    private final int userId;
    private final String fullName;
    private final String lokasi;
    private final String namaHewan;
    private final int beratBadan;
    private final int harga;
    private final String jenisKelamin;
    private final int attachmentId;
    private final String attachmentUrl;
    private final String deskripsi;
    private int isFavorite;
    private final String createdAt;
    private final String updatedAt;

    public PetEntity(
            long id,
            int usia,
            String kondisi,
            int rasId,
            String ras,
            int userId,
            String fullName,
            String lokasi,
            String namaHewan,
            int beratBadan,
            int harga,
            String jenisKelamin,
            int attachmentId,
            String attachmentUrl,
            String deskripsi,
            int isFavorite,
            String createdAt,
            String updatedAt) {
        this.id = id;
        this.usia = usia;
        this.kondisi = kondisi;
        this.rasId = rasId;
        this.ras = ras;
        this.userId = userId;
        this.fullName = fullName;
        this.lokasi = lokasi;
        this.namaHewan = namaHewan;
        this.beratBadan = beratBadan;
        this.harga = harga;
        this.jenisKelamin = jenisKelamin;
        this.attachmentId = attachmentId;
        this.attachmentUrl = attachmentUrl;
        this.deskripsi = deskripsi;
        this.isFavorite = isFavorite;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    protected PetEntity(Parcel in) {
        id = in.readLong();
        usia = in.readInt();
        kondisi = in.readString();
        rasId = in.readInt();
        ras = in.readString();
        userId = in.readInt();
        fullName = in.readString();
        lokasi = in.readString();
        namaHewan = in.readString();
        beratBadan = in.readInt();
        harga = in.readInt();
        jenisKelamin = in.readString();
        attachmentId = in.readInt();
        attachmentUrl = in.readString();
        deskripsi = in.readString();
        isFavorite = in.readInt();
        createdAt = in.readString();
        updatedAt = in.readString();
    }

    public long getId() {
        return id;
    }

    public int getUsia() {
        return usia;
    }

    public String getKondisi() {
        return kondisi;
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

    public int getHarga() {
        return harga;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public int getAttachmentId() {
        return attachmentId;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getIsFavorite() {
        return isFavorite;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getAttachmentUrl() {
        return attachmentUrl;
    }

    public String getRas() {
        return ras;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setIsFavorite(int isFavorite){
        this.isFavorite = isFavorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(usia);
        dest.writeString(kondisi);
        dest.writeInt(rasId);
        dest.writeString(ras);
        dest.writeInt(userId);
        dest.writeString(fullName);
        dest.writeString(lokasi);
        dest.writeString(namaHewan);
        dest.writeInt(beratBadan);
        dest.writeInt(harga);
        dest.writeString(jenisKelamin);
        dest.writeInt(attachmentId);
        dest.writeString(attachmentUrl);
        dest.writeString(deskripsi);
        dest.writeInt(isFavorite);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "id=" + id +
                ", usia=" + usia +
                ", kondisi='" + kondisi + '\'' +
                ", rasId=" + rasId +
                ", ras='" + ras + '\'' +
                ", userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", lokasi='" + lokasi + '\'' +
                ", namaHewan='" + namaHewan + '\'' +
                ", beratBadan=" + beratBadan +
                ", harga=" + harga +
                ", jenisKelamin='" + jenisKelamin + '\'' +
                ", attachmentId=" + attachmentId +
                ", attachmentUrl='" + attachmentUrl + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                ", isFavorite=" + isFavorite +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
