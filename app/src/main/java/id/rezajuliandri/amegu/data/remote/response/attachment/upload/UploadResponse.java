package id.rezajuliandri.amegu.data.remote.response.attachment.upload;

public class UploadResponse{
	private int code;
	private AttachmentResponse data;
	private String message;

	public int getCode(){
		return code;
	}

	public AttachmentResponse getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}
