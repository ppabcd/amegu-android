package id.rezajuliandri.amegu.api.responses.data;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Kota implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("provinsiId")
    @Expose
    private long provinsiId;
    @SerializedName("provinsi")
    @Expose
    private Provinsi provinsi;
    public final static Creator<Kota> CREATOR = new Creator<Kota>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Kota createFromParcel(android.os.Parcel in) {
            return new Kota(in);
        }

        public Kota[] newArray(int size) {
            return (new Kota[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6913883303438001734L;

    protected Kota(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.nama = ((String) in.readValue((String.class.getClassLoader())));
        this.provinsiId = ((long) in.readValue((long.class.getClassLoader())));
        this.provinsi = ((Provinsi) in.readValue((Provinsi.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Kota() {
    }

    /**
     *
     * @param provinsi
     * @param provinsiId
     * @param nama
     * @param id
     */
    public Kota(long id, String nama, long provinsiId, Provinsi provinsi) {
        super();
        this.id = id;
        this.nama = nama;
        this.provinsiId = provinsiId;
        this.provinsi = provinsi;
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

    public long getProvinsiId() {
        return provinsiId;
    }

    public void setProvinsiId(long provinsiId) {
        this.provinsiId = provinsiId;
    }

    public Provinsi getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(Provinsi provinsi) {
        this.provinsi = provinsi;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Kota.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("nama");
        sb.append('=');
        sb.append(((this.nama == null)?"<null>":this.nama));
        sb.append(',');
        sb.append("provinsiId");
        sb.append('=');
        sb.append(this.provinsiId);
        sb.append(',');
        sb.append("provinsi");
        sb.append('=');
        sb.append(((this.provinsi == null)?"<null>":this.provinsi));
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
        dest.writeValue(provinsiId);
        dest.writeValue(provinsi);
    }

    public int describeContents() {
        return 0;
    }

}