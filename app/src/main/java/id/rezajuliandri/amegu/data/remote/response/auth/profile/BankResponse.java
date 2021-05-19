package id.rezajuliandri.amegu.data.remote.response.auth.profile;

import com.google.gson.annotations.SerializedName;

public class BankResponse {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public String getCode(){
		return code;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}