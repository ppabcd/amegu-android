package id.rezajuliandri.amegu.data.entity.location;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Hasil response dari data provinsi yang diminta dari database
 */
public class Provinsi implements Serializable, Parcelable, Comparable<Provinsi> {

    public final static Creator<Provinsi> CREATOR = new Creator<Provinsi>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Provinsi createFromParcel(android.os.Parcel in) {
            return new Provinsi(in);
        }

        public Provinsi[] newArray(int size) {
            return (new Provinsi[size]);
        }

    };
    private final static long serialVersionUID = -477540211121387663L;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("nama")
    @Expose
    private String nama;

    protected Provinsi(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Provinsi() {
    }

    /**
     * @param nama
     * @param id
     */
    public Provinsi(long id, String nama) {
        super();
        this.id = id;
        this.nama = nama;
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

    @Override
    public String toString() {
        return nama;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int compareTo(Provinsi another) {
        return (int) (this.id - another.getId());
    }
}