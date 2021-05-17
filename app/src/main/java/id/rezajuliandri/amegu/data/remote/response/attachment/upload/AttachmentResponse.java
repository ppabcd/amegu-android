package id.rezajuliandri.amegu.data.remote.response.attachment.upload;

import id.rezajuliandri.amegu.data.remote.response.auth.profile.UserProfileResponse;

public class AttachmentResponse {
	private String createdAt;
	private String filename;
	private String mimetype;
	private int id;
	private int userId;
	private UserProfileResponse user;
	private int hewanId;
	private String url;
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public String getFilename(){
		return filename;
	}

	public String getMimetype(){
		return mimetype;
	}

	public int getId(){
		return id;
	}

	public int getUserId(){
		return userId;
	}

	public UserProfileResponse getUser(){
		return user;
	}

	public int getHewanId(){
		return hewanId;
	}

	public String getUrl(){
		return url;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	@Override
	public String toString() {
		return "AttachmentResponse{" +
				"createdAt='" + createdAt + '\'' +
				", filename='" + filename + '\'' +
				", mimetype='" + mimetype + '\'' +
				", id=" + id +
				", userId=" + userId +
				", user=" + user +
				", hewanId=" + hewanId +
				", url='" + url + '\'' +
				", updatedAt='" + updatedAt + '\'' +
				'}';
	}
}
