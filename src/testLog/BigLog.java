package testLog;

import java.time.LocalDateTime;
import java.util.List;

public class BigLog {

	private LocalDateTime outputTime;
	private Integer countOfResponseSpeedUnder500; // 500ms以下
	private Integer countOfResponseSpeedUnder2000; // 2000ms以下
	private Integer countOfResopnseSpeedOver2001; // 2001ms以下
	private Integer aveSpeed;
	private List<Log> logList;

	public BigLog() {
	}

	public BigLog(LocalDateTime outputTime, Integer countOfResponseSpeedUnder500, Integer countOfResponseSpeedUnder2000,
			Integer countOfResopnseSpeedOver2001, Integer aveSpeed, List<Log> logList) {
		super();
		this.outputTime = outputTime;
		this.countOfResponseSpeedUnder500 = countOfResponseSpeedUnder500;
		this.countOfResponseSpeedUnder2000 = countOfResponseSpeedUnder2000;
		this.countOfResopnseSpeedOver2001 = countOfResopnseSpeedOver2001;
		this.aveSpeed = aveSpeed;
		this.logList = logList;
	}

	public void calcResponseSpeed() {
		this.logList.forEach(log -> {
			if (log.getResponseSpeed() <= 500) {
				this.countOfResponseSpeedUnder500++;
			} else if (log.getResponseSpeed() <= 2000) {
				this.countOfResponseSpeedUnder2000++;
			} else if (log.getResponseSpeed() >= 2001) {
				this.countOfResopnseSpeedOver2001++;
			}
			if (logList.size() != 0)
				this.aveSpeed += log.getResponseSpeed();
		});
	}

	public void calcAveSpeed() {
		int divCount = 1;
		if (this.logList.size() != 0)
			divCount = this.logList.size();
		this.aveSpeed = this.aveSpeed / divCount;
	}

	public LocalDateTime getOutputTime() {
		return outputTime;
	}

	public void setOutputTime(LocalDateTime outputTime) {
		this.outputTime = outputTime;
	}

	public Integer getCountOfResponseSpeedUnder500() {
		return countOfResponseSpeedUnder500;
	}

	public void setCountOfResponseSpeedUnder500(Integer countOfResponseSpeedUnder500) {
		this.countOfResponseSpeedUnder500 = countOfResponseSpeedUnder500;
	}

	public Integer getCountOfResponseSpeedUnder2000() {
		return countOfResponseSpeedUnder2000;
	}

	public void setCountOfResponseSpeedUnder2000(Integer countOfResponseSpeedUnder2000) {
		this.countOfResponseSpeedUnder2000 = countOfResponseSpeedUnder2000;
	}

	public Integer getCountOfResopnseSpeedOver2001() {
		return countOfResopnseSpeedOver2001;
	}

	public void setCountOfResopnseSpeedOver2001(Integer countOfResopnseSpeedOver2001) {
		this.countOfResopnseSpeedOver2001 = countOfResopnseSpeedOver2001;
	}

	public List<Log> getLogList() {
		return logList;
	}

	public void setLogList(List<Log> logList) {
		this.logList = logList;
	}

	public Integer getAveSpeed() {
		return aveSpeed;
	}

	public void setAveSpeed(Integer aveSpeed) {
		this.aveSpeed = aveSpeed;
	}

	@Override
	public String toString() {
		return "BigLog [outputTime=" + outputTime + ", countOfResponseSpeedUnder500=" + countOfResponseSpeedUnder500
				+ ", countOfResponseSpeedUnder2000=" + countOfResponseSpeedUnder2000 + ", countOfResopnseSpeedOver2001="
				+ countOfResopnseSpeedOver2001 + ", aveSpeed=" + aveSpeed + ", logList=" + logList + "]";
	}

}
