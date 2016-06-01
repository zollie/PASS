package com.statistics.hadoop.kz.week2;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.DateTime;

public class CommonLogTuple {
	private String host;
	private String rfc931;
	private String username;
	private DateTime dateTime;
	private String request;
	private String statusCode;
	private int bytes;
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getRfc931() {
		return rfc931;
	}
	public void setRfc931(String rfc931) {
		this.rfc931 = rfc931;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public DateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(DateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public int getBytes() {
		return bytes;
	}
	public void setBytes(int bytes) {
		this.bytes = bytes;
	}
	
	@Override
	public boolean equals(Object obj) {
		CommonLogTuple other = (CommonLogTuple)obj;
		if(other == null) return false;
		return new EqualsBuilder()
				.append(host, other.getHost())
				.append(rfc931, other.getRfc931())
				.append(username, other.getUsername())
				.append(dateTime,  other.getDateTime())
				.append(request, other.getRequest())
				.append(statusCode, other.getStatusCode())
				.append(bytes, other.getBytes())
				.isEquals();
	}
	
	@Override
	public String toString() {
		return
		new ToStringBuilder(this)
		.append("host", host)
		.append("rfc931", rfc931)
		.append("username", username)
		.append("dateTime", dateTime)
		.append("request", request)
		.append("statusCode", statusCode)
		.append("bytes", bytes)
		.toString();
	}
}
