package id.rezajuliandri.amegu.entity;

import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity
public class Alamat implements Serializable, Parcelable {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    @SerializedName("_id")
    @Expose
    private long id;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("kelurahanName")
    @Expose
    private String kelurahanName;
    @SerializedName("kecamatanName")
    @Expose
    private String kecamatanName;
    @SerializedName("kotaName")
    @Expose
    private String kotaName;
    @SerializedName("provinsiName")
    @Expose
    private String provinsiName;
    public final static Creator<Alamat> CREATOR = new Creator<Alamat>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Alamat createFromParcel(android.os.Parcel in) {
            return new Alamat(in);
        }

        public Alamat[] newArray(int size) {
            return (new Alamat[size]);
        }

    }
            ;
    private final static long serialVersionUID = -4116725329306222880L;

    protected Alamat(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.alamat = ((String) in.readValue((String.class.getClassLoader())));
        this.kelurahanName = ((String) in.readValue((String.class.getClassLoader())));
        this.kecamatanName = ((String) in.readValue((String.class.getClassLoader())));
        this.kotaName = ((String) in.readValue((String.class.getClassLoader())));
        this.provinsiName = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Alamat() {
    }

    /**
     *
     * @param kecamatanName
     * @param kotaName
     * @param kotaId
     * @param kecamatanId
     * @param provinsiName
     * @param alamat
     * @param kelurahanId
     * @param provinsiId
     * @param id
     * @param kelurahanName
     */
    public Alamat(long id, String alamat, long kelurahanId, String kelurahanName, long kecamatanId, String kecamatanName, long kotaId, String kotaName, Object provinsiId, String provinsiName) {
        super();
        this.id = id;
        this.alamat = alamat;
        this.kelurahanName = kelurahanName;
        this.kecamatanName = kecamatanName;
        this.kotaName = kotaName;
        this.provinsiName = provinsiName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKelurahanName() {
        return kelurahanName;
    }

    public void setKelurahanName(String kelurahanName) {
        this.kelurahanName = kelurahanName;
    }


    public String getKecamatanName() {
        return kecamatanName;
    }

    public void setKecamatanName(String kecamatanName) {
        this.kecamatanName = kecamatanName;
    }



    public String getKotaName() {
        return kotaName;
    }

    public void setKotaName(String kotaName) {
        this.kotaName = kotaName;
    }


    public String getProvinsiName() {
        return provinsiName;
    }

    public void setProvinsiName(String provinsiName) {
        this.provinsiName = provinsiName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Alamat.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(this.id);
        sb.append(',');
        sb.append("alamat");
        sb.append('=');
        sb.append(((this.alamat == null)?"<null>":this.alamat));
        sb.append(',');
        sb.append("kelurahanName");
        sb.append('=');
        sb.append(((this.kelurahanName == null)?"<null>":this.kelurahanName));
        sb.append(',');
        sb.append("kecamatanName");
        sb.append('=');
        sb.append(((this.kecamatanName == null)?"<null>":this.kecamatanName));
        sb.append(',');
        sb.append(',');
        sb.append("kotaName");
        sb.append('=');
        sb.append(((this.kotaName == null)?"<null>":this.kotaName));
        sb.append(',');
        sb.append("provinsiName");
        sb.append('=');
        sb.append(((this.provinsiName == null)?"<null>":this.provinsiName));
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
        dest.writeValue(alamat);
        dest.writeValue(kelurahanName);
        dest.writeValue(kecamatanName);
        dest.writeValue(kotaName);
        dest.writeValue(provinsiName);
    }

    public int describeContents() {
        return 0;
    }
}
