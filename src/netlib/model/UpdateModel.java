package netlib.model;

public class UpdateModel extends BaseModel {

	private static final long serialVersionUID = -2091551348343415124L;
	private boolean isCurrentVersion;
	private String message;
	private String newVersionUrl;

	private int type;

	public boolean isCurrentVersion() {
		return isCurrentVersion;
	}

	public void setCurrentVersion(boolean isCurrentVersion) {
		this.isCurrentVersion = isCurrentVersion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNewVersionUrl() {
		return newVersionUrl;
	}

	public void setNewVersionUrl(String newVersionUrl) {
		this.newVersionUrl = newVersionUrl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}