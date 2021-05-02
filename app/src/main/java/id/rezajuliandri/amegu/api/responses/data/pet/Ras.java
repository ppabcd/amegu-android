package id.rezajuliandri.amegu.api.responses.data.pet;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ras implements Serializable, Parcelable, Comparable<Ras>
{

    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("ras")
    @Expose
    private String ras;
    @SerializedName("jenisId")
    @Expose
    private long jenisId;
    @SerializedName("jenis")
    @Expose
    private Jenis jenis;
    public final static Creator<Jenis> CREATOR = new Creator<Jenis>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Jenis createFromParcel(android.os.Parcel in) {
            return new Jenis(in);
        }

        public Jenis[] newArray(int size) {
            return (new Jenis[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6144155418404928565L;

    protected Ras(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.ras = ((String) in.readValue((String.class.getClassLoader())));
        this.jenisId = ((long) in.readValue((long.class.getClassLoader())));
        this.jenis = ((Jenis) in.readValue((Jenis.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Ras() {
    }

    /**
     *
     * @param ras
     * @param jenisId
     * @param jenis
     * @param id
     */
    public Ras(long id, String ras, long jenisId, Jenis jenis) {
        super();
        this.id = id;
        this.ras = ras;
        this.jenisId = jenisId;
        this.jenis = jenis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRas() {
        return ras;
    }

    public void setRas(String ras) {
        this.ras = ras;
    }

    public long getJenisId() {
        return jenisId;
    }

    public void setJenisId(long jenisId) {
        this.jenisId = jenisId;
    }

    public Jenis getJenis() {
        return jenis;
    }

    public void setJenis(Jenis jenis) {
        this.jenis = jenis;
    }

    @Override
    public String toString() {
        return ras;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(ras);
        dest.writeValue(jenisId);
        dest.writeValue(jenis);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int compareTo(Ras another) {
        return (int) (this.id - another.getId());
    }
}