package id.rezajuliandri.amegu.api.responses.data.pet;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import id.rezajuliandri.amegu.entity.Users;

public class Attachment implements Serializable, Parcelable, Comparable<Attachment> {
	@SerializedName("id")
	private int id;
	@SerializedName("userId")
	private int userId;
	@SerializedName("user")
	private Users user;
	@SerializedName("hewanId")
    private int hewanId;
	@SerializedName("url")
    private String url;
	@SerializedName("createdAt")
	private String createdAt;
	@SerializedName("updatedAt")
    private String updatedAt;
	@SerializedName("filename")
	private String filename;
	@SerializedName("mimetype")
	private String mimetype;

    protected Attachment(Parcel in) {
        createdAt = in.readString();
        filename = in.readString();
        mimetype = in.readString();
        id = in.readInt();
        userId = in.readInt();
        hewanId = in.readInt();
        url = in.readString();
        updatedAt = in.readString();
    }

    public static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel in) {
            return new Attachment(in);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };

    public String getCreatedAt() {
        return createdAt;
    }

    public String getFilename() {
        return filename;
    }

    public String getMimetype() {
        return mimetype;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public Users getUser() {
        return user;
    }

    public int getHewanId() {
        return hewanId;
    }

    public String getUrl() {
        return url;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(createdAt);
        dest.writeString(filename);
        dest.writeString(mimetype);
        dest.writeInt(id);
        dest.writeInt(userId);
        dest.writeInt(hewanId);
        dest.writeString(url);
        dest.writeString(updatedAt);
    }

    @Override
    public int compareTo(Attachment another) {
        return (int) (this.id - another.getId());
    }

}
