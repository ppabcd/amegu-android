package id.rezajuliandri.amegu.api.responses.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kecamatan implements Serializable, Parcelable
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
        StringBuilder sb = new StringBuilder();
        sb.append(Kecamatan.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("nama");
        sb.append('=');
        sb.append(((this.nama == null)?"<null>":this.nama));
        sb.append(',');
        sb.append("kotaId");
        sb.append('=');
        sb.append(this.kotaId);
        sb.append(',');
        sb.append("kota");
        sb.append('=');
        sb.append(((this.kota == null)?"<null>":this.kota));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
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

}
