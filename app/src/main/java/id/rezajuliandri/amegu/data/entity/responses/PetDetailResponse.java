package id.rezajuliandri.amegu.data.entity.responses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import id.rezajuliandri.amegu.data.entity.pet.Pet;

public class PetDetailResponse implements Serializable, Parcelable {

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private Pet data;

	@SerializedName("message")
	private String message;

	protected PetDetailResponse(Parcel in) {
		code = in.readInt();
		data = in.readParcelable(Pet.class.getClassLoader());
		message = in.readString();
	}

	public static final Creator<PetDetailResponse> CREATOR = new Creator<PetDetailResponse>() {
		@Override
		public PetDetailResponse createFromParcel(Parcel in) {
			return new PetDetailResponse(in);
		}

		@Override
		public PetDetailResponse[] newArray(int size) {
			return new PetDetailResponse[size];
		}
	};

	public int getCode(){
		return code;
	}

	public Pet getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"PetDetailResponse{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(code);
		dest.writeParcelable(data, flags);
		dest.writeString(message);
	}
}