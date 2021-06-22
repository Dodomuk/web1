package namoosori.fileserver.util;

import com.google.gson.Gson;

public class RequestMessage {
	//
	private String serviceName; 
	private String value; 
	private String remark; 
	
	public RequestMessage(String serviceName, String value) {
		//
		this.serviceName = serviceName; 
		this.value = value; 
	}
	
	@Override
	public String toString() {
		// 
		StringBuilder builder = new StringBuilder(); 
		
		builder.append("Service name:").append(serviceName); 
		builder.append(", value:").append(value);
		builder.append(", remark:").append(remark);
		
		return builder.toString(); 
	}
	
	public String toJson() {
		// 
		return (new Gson()).toJson(this); 
	}
	
	public static RequestMessage fromJson(String json) {
		// 
		return (new Gson()).fromJson(json, RequestMessage.class); 
	}
	
	public static RequestMessage getSample() {
		// 
		return new RequestMessage("setBaseTime", "10"); 
	}
	
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}