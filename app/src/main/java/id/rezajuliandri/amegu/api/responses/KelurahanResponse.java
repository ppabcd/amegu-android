package id.rezajuliandri.amegu.api.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

import id.rezajuliandri.amegu.api.responses.data.location.Kelurahan;
/**
 * Hasil response dari data kelurahan yang diminta dari database
 */
public class KelurahanResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private ArrayList<Kelurahan> data = null;
    public final static Creator<KelurahanResponse> CREATOR = new Creator<KelurahanResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public KelurahanResponse createFromParcel(android.os.Parcel in) {
            return new KelurahanResponse(in);
        }

        public KelurahanResponse[] newArray(int size) {
            return (new KelurahanResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3970989241040340302L;

    protected KelurahanResponse(android.os.Parcel in) {
        in.readList(this.data, (Kelurahan.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public KelurahanResponse() {
    }

    /**
     *
     * @param data
     */
    public KelurahanResponse(ArrayList<Kelurahan> data) {
        super();
        this.data = data;
    }

    public ArrayList<Kelurahan> getData() {
        return data;
    }

    public void setData(ArrayList<Kelurahan> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(KelurahanResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
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

