package id.rezajuliandri.amegu.data.remote.response.pet.adopsidetail;

import com.google.gson.annotations.SerializedName;

import id.rezajuliandri.amegu.data.remote.response.attachment.upload.AttachmentResponse;
import id.rezajuliandri.amegu.data.remote.response.auth.login.UserLoginResponse;

public class InvoiceResponse {

	@SerializedName("owner")
	private UserLoginResponse owner;

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("amount")
	private int amount;

	@SerializedName("total")
	private int total;

	@SerializedName("attachment")
	private AttachmentResponse attachment;

	@SerializedName("admin")
	private int admin;

	@SerializedName("id")
	private int id;

	@SerializedName("attachmentId")
	private long attachmentId;

	@SerializedName("invoiceNo")
	private String invoiceNo;

	@SerializedName("ownerId")
	private int ownerId;

	@SerializedName("adopsiId")
	private int adopsiId;

	@SerializedName("updatedAt")
	private String updatedAt;

	public UserLoginResponse getOwner(){
		return owner;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public int getAmount(){
		return amount;
	}

	public int getTotal(){
		return total;
	}

	public AttachmentResponse getAttachment(){
		return attachment;
	}

	public int getAdmin(){
		return admin;
	}

	public int getId(){
		return id;
	}

	public long getAttachmentId(){
		return attachmentId;
	}

	public String getInvoiceNo(){
		return invoiceNo;
	}

	public int getOwnerId(){
		return ownerId;
	}

	public int getAdopsiId(){
		return adopsiId;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}