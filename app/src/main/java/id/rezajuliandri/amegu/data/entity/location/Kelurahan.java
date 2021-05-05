package id.rezajuliandri.amegu.data.entity.location;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Hasil response dari data kelurahan yang diminta dari database
 */
public class Kelurahan implements Serializable, Parcelable, Comparable<Kelurahan> {

    public final static Creator<Kelurahan> CREATOR = new Creator<Kelurahan>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Kelurahan createFromParcel(android.os.Parcel in) {
            return new Kelurahan(in);
        }

        public Kelurahan[] newArray(int size) {
            return (new Kelurahan[size]);
        }

    };
    private final static long serialVersionUID = -5089576641172758349L;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kecamatanId")
    @Expose
    private long kecamatanId;
    @SerializedName("kecamatan")
    @Expose
    private Kecamatan kecamatan;

    protected Kelurahan(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.kecamatanId = ((long) in.readValue((long.class.getClassLoader())));
        this.kecamatan = ((Kecamatan) in.readValue((Kecamatan.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Kelurahan() {
    }

    /**
     * @param nama
     * @param kecamatanId
     * @param kecamatan
     * @param id
     */
    public Kelurahan(long id, String nama, long kecamatanId, Kecamatan kecamatan) {
        super();
        this.id = id;
        this.nama = nama;
        this.kecamatanId = kecamatanId;
        this.kecamatan = kecamatan;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public long getKecamatanId() {
        return kecamatanId;
    }

    public void setKecamatanId(long kecamatanId) {
        this.kecamatanId = kecamatanId;
    }

    public Kecamatan getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(Kecamatan kecamatan) {
        this.kecamatan = kecamatan;
    }

    @Override
    public String toString() {
        return nama;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
        dest.writeValue(kecamatanId);
        dest.writeValue(kecamatan);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int compareTo(Kelurahan another) {
        return (int) (this.id - another.getId());
    }
}
