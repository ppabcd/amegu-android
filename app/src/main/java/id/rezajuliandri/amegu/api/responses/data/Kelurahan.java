package id.rezajuliandri.amegu.api.responses.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kelurahan implements Serializable, Parcelable
{

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

    }
            ;
    private final static long serialVersionUID = -5089576641172758349L;

    protected Kelurahan(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.kecamatanId = ((long) in.readValue((long.class.getClassLoader())));
        this.kecamatan = ((Kecamatan) in.readValue((Kecamatan.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Kelurahan() {
    }

    /**
     *
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
        StringBuilder sb = new StringBuilder();
        sb.append(Kelurahan.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("nama");
        sb.append('=');
        sb.append(((this.nama == null)?"<null>":this.nama));
        sb.append(',');
        sb.append("kecamatanId");
        sb.append('=');
        sb.append(this.kecamatanId);
        sb.append(',');
        sb.append("kecamatan");
        sb.append('=');
        sb.append(((this.kecamatan == null)?"<null>":this.kecamatan));
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
        dest.writeValue(kecamatanId);
        dest.writeValue(kecamatan);
    }

    public int describeContents() {
        return 0;
    }

}
