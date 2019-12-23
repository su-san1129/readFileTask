package testLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Test2 {

	public void outputText() {
		try {
			File file = new File("C:\\Users\\devia\\Desktop\\アクセスログ.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			LocalDateTime logTime = LocalDateTime.of(2012, 4, 7, 0, 0, 0);
			List<Log> logList = new ArrayList<>();
			List<BigLog> bigLogList = new ArrayList<>();

			while (str != null) {
				str = br.readLine();
				if (str != null) {
					String[] data = str.split("	");
					LocalDateTime inputDate = LocalDateTime.parse(data[0],
							DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
					int responseSpeed = Integer.parseInt(data[2]);
					Log log = new Log(inputDate, data[1], responseSpeed);
					logList.add(log);
				}
			}
			while (true) {
				BigLog bigLog = new BigLog(logTime, 0, 0, 0, 0, new ArrayList<>());
				bigLogList.add(bigLog);
				logList.stream()
						.filter(log -> log.getLogEndTime().isAfter(bigLog.getOutputTime().minusSeconds(1))
								&& log.getLogEndTime().isBefore(bigLog.getOutputTime().plusMinutes(5)))
						.forEach(log -> bigLog.getLogList().add(log));
				logTime = logTime.plusMinutes(5);
				if (logTime.isAfter(LocalDateTime.of(2012, 4, 7, 23, 55))) {
					break;
				}
			}
			bigLogList.forEach(bigLog -> {
				bigLog.calcResponseSpeed();
				bigLog.calcAveSpeed();
				System.out.println(
						bigLog.getOutputTime() + " " 
						+ bigLog.getCountOfResponseSpeedUnder500() + " "
						+ bigLog.getCountOfResponseSpeedUnder2000() + " " 
						+ bigLog.getCountOfResopnseSpeedOver2001() + " "
						+ bigLog.getAveSpeed()
						);
			});

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
