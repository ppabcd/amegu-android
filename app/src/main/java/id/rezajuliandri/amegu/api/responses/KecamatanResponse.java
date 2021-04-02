package id.rezajuliandri.amegu.api.responses;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import id.rezajuliandri.amegu.api.responses.data.Kecamatan;

public class KecamatanResponse implements Serializable, Parcelable
{

    @SerializedName("data")
    @Expose
    private List<Kecamatan> data = null;
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

    }
            ;
    private final static long serialVersionUID = 7507502251709621159L;

    protected KecamatanResponse(android.os.Parcel in) {
        in.readList(this.data, (Kecamatan.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public KecamatanResponse() {
    }

    /**
     *
     * @param data
     */
    public KecamatanResponse(List<Kecamatan> data) {
        super();
        this.data = data;
    }

    public List<Kecamatan> getData() {
        return data;
    }

    public void setData(List<Kecamatan> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(KotaResponse.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
