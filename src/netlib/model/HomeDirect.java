package netlib.model;

public class HomeDirect {
	int id;
	int image;
	String name;
	String desc;

	public HomeDirect() {
		super();
	}

	public HomeDirect(int id, int image, String name, String desc) {
		this.id = id;
		this.image = image;
		this.name = name;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
