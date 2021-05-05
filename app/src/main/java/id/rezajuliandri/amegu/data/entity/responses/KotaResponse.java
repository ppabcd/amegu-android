package id.rezajuliandri.amegu.data.entity.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import id.rezajuliandri.amegu.data.entity.location.Kota;

/**
 * Hasil response dari data kota yang diminta dari database
 */
public class KotaResponse implements Serializable, Parcelable {

    public final static Creator<KotaResponse> CREATOR = new Creator<KotaResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public KotaResponse createFromParcel(android.os.Parcel in) {
            return new KotaResponse(in);
        }

        public KotaResponse[] newArray(int size) {
            return (new KotaResponse[size]);
        }

    };
    private final static long serialVersionUID = 7507502251709621159L;
    @SerializedName("data")
    @Expose
    private ArrayList<Kota> data = null;

    protected KotaResponse(android.os.Parcel in) {
        in.readList(this.data, (Kota.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public KotaResponse() {
    }

    /**
     * @param data
     */
    public KotaResponse(ArrayList<Kota> data) {
        super();
        this.data = data;
    }

    public ArrayList<Kota> getData() {
        return data;
    }

    public void setData(ArrayList<Kota> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(KotaResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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