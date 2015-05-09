package netlib.model;import java.io.Serializable;import com.google.gson.annotations.SerializedName;/** * @author henzil * @E-mail lizhen@dns.com.cn * @version create time:2013-7-22_下午1:59:20 * @Description 所有model 基类。 */public abstract class BaseModel implements Serializable {	/**	 * 	 */	private static final long serialVersionUID = 5268625605268545266L;	@SerializedName("code")	private int code;	@SerializedName("msg")	private String msg;	@SerializedName("message")    private String message;	@SerializedName("isCache")    public boolean isCache;	@SerializedName("cacheKey")    public String cacheKey;    public String getMessage() {        return message;    }    public void setMessage(String message) {        this.message = message;    }	public static long getSerialversionuid() {		return serialVersionUID;	}	public int getCode() {		return code;	}	public void setCode(int code) {		this.code = code;	}	public String getMsg() {		return msg;	}	public void setMsg(String msg) {		this.msg = msg;	}}