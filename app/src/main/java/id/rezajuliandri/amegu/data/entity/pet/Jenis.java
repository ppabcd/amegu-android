package id.rezajuliandri.amegu.data.entity.pet;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Jenis implements Serializable, Parcelable, Comparable<Jenis> {

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

    };
    private final static long serialVersionUID = -389749478017725121L;
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("jenis")
    @Expose
    private String jenis;

    protected Jenis(android.os.Parcel in) {
        this.id = ((long) in.readValue((long.class.getClassLoader())));
        this.jenis = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Jenis() {
    }

    /**
     * @param jenis
     * @param id
     */
    public Jenis(long id, String jenis) {
        super();
        this.id = id;
        this.jenis = jenis;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @Override
    public String toString() {
        return jenis;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(jenis);
    }

    public int describeContents() {
        return 0;
    }

    @Override
    public int compareTo(Jenis another) {
        return (int) (this.id - another.getId());
    }
}