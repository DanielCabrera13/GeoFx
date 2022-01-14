
package dad.geofx.api.ipapi;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Connection {
	
	@SerializedName("asn")
    @Expose
	private Integer asn;
	
	@SerializedName("isp")
    @Expose
	private String isp;


	public Integer getAsn() {
		return asn;
	}

	public void setAsn(Integer asn) {
		this.asn = asn;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

}
