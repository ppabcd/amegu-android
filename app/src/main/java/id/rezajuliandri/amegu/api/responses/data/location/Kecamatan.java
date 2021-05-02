package id.rezajuliandri.amegu.api.responses.data.location;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
/**
 * Hasil response dari data kecamatan yang diminta dari database
 */
public class Kecamatan implements Serializable, Parcelable, Comparable<Kecamatan>
{

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("kotaId")
    @Expose
    private long kotaId;
    @SerializedName("kota")
    @Expose
    private Kota kota;
    public final static Creator<Kecamatan> CREATOR = new Creator<Kecamatan>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Kecamatan createFromParcel(android.os.Parcel in) {
            return new Kecamatan(in);
        }

        public Kecamatan[] newArray(int size) {
            return (new Kecamatan[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5137545460355603326L;

    protected Kecamatan(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.kotaId = ((long) in.readValue((long.class.getClassLoader())));
        this.kota = ((Kota) in.readValue((Kota.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Kecamatan() {
    }

    /**
     *
     * @param kota
     * @param nama
     * @param kotaId
     * @param id
     */
    public Kecamatan(long id, String nama, long kotaId, Kota kota) {
        super();
        this.id = id;
        this.nama = nama;
        this.kotaId = kotaId;
        this.kota = kota;
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

    public long getKotaId() {
        return kotaId;
    }

    public void setKotaId(long kotaId) {
        this.kotaId = kotaId;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    @Override
    public String toString() {
        return nama;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(nama);
        dest.writeValue(kotaId);
        dest.writeValue(kota);
    }


    public int describeContents() {
        return 0;
    }

    @Override
    public int compareTo(Kecamatan another) {
        return (int) (this.id - another.getId());
    }
}
