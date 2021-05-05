package id.rezajuliandri.amegu.data.entity.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.location.Provinsi;

/**
 * Hasil response dari data provinsi yang diminta dari database
 */
public class ProvinsiResponse implements Serializable, Parcelable {
    public final static Creator<ProvinsiResponse> CREATOR = new Creator<ProvinsiResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProvinsiResponse createFromParcel(android.os.Parcel in) {
            return new ProvinsiResponse(in);
        }

        public ProvinsiResponse[] newArray(int size) {
            return (new ProvinsiResponse[size]);
        }

    };
    private final static long serialVersionUID = 3018428344951074750L;
    @SerializedName("data")
    @Expose
    private ArrayList<Provinsi> data = null;

    protected ProvinsiResponse(android.os.Parcel in) {
        in.readList(this.data, (Provinsi.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public ProvinsiResponse() {
    }

    /**
     * @param data
     */
    public ProvinsiResponse(ArrayList<Provinsi> data) {
        super();
        this.data = data;
    }

    public ArrayList<Provinsi> getData() {
        return data;
    }

    public void setData(ArrayList<Provinsi> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ProvinsiResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null) ? "<null>" : this.data));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}