package testLog;

import java.time.LocalDateTime;

public class Log {

	private LocalDateTime logEndTime;
	private String page;
	private Integer responseSpeed;
	
	public Log() {}
	
	public Log(LocalDateTime logEndTime, String page, Integer responseSpeed) {
		super();
		this.logEndTime = logEndTime;
		this.page = page;
		this.responseSpeed = responseSpeed;
	}

	public LocalDateTime getLogEndTime() {
		return logEndTime;
	}

	public void setLogEndTime(LocalDateTime logEndTime) {
		this.logEndTime = logEndTime;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Integer getResponseSpeed() {
		return responseSpeed;
	}

	public void setResponseSpeed(Integer responseSpeed) {
		this.responseSpeed = responseSpeed;
	}

	@Override
	public String toString() {
		return "Log [logEndTime=" + logEndTime + ", page=" + page + ", responseSpeed=" + responseSpeed + "]";
	}
	
	

}