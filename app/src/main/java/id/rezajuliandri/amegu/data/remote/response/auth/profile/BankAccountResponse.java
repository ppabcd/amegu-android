package id.rezajuliandri.amegu.data.remote.response.auth.profile;

import com.google.gson.annotations.SerializedName;

public class BankAccountResponse{

	@SerializedName("bankAccount")
	private String bankAccount;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("bankId")
	private int bankId;

	@SerializedName("bank")
	private BankResponse bank;

	@SerializedName("ownerName")
	private String ownerName;

	@SerializedName("id")
	private int id;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getBankAccount(){
		return bankAccount;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getBankId(){
		return bankId;
	}

	public BankResponse getBank(){
		return bank;
	}

	public String getOwnerName(){
		return ownerName;
	}

	public int getId(){
		return id;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}