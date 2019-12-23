package testLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Test {

	public void txtOutput() {
		long startTime = System.currentTimeMillis();
		try {
			File file = new File("C:\\Users\\devia\\Desktop\\アクセスログ.txt");
			BufferedReader br = new BufferedReader(new FileReader(file));
			String str = br.readLine();
			LocalTime logStartTime = LocalTime.of(0, 0); // 結果出力用
			LocalDateTime logEndTime = LocalDateTime.of(2012, 4, 7, 0, 5, 0); // log集計の境界値
			int countOfResponseSpeedUnder500 = 0; // 500ms以下
			int countOfResponseSpeedUnder2000 = 0; // 2000ms以下
			int countOfResopnseSpeedOver2001 = 0; // 2001ms以下
			int sum = 0; // 応答時間の合計
			int inputCount = 0; // 要素のカウント数.
			LocalDateTime finalTime = null;
			System.out.println("時刻     |	 500ms以下 |  2000ms以下  | 2001ms以上 |  平均応答時間(ms)");
			System.out.println("---------------------------------------------------------------");
			while (str != null) {
				str = br.readLine();
				if (str != null) {
					String[] data = str.split("	"); // データを日時、ページ、応答時間で分割.
					LocalDateTime inputDate = LocalDateTime.parse(data[0],
							DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")); // 変換した形式.
					int responseSpeed = Integer.parseInt(data[2]);
					if (inputDate.isAfter(logEndTime)) {
						if (inputCount == 0) {
							inputCount = 1; // もし、1件もデータがなければ、1で初期化.
						}
						resultToConsole(logStartTime, countOfResponseSpeedUnder500, countOfResponseSpeedUnder2000,
								countOfResopnseSpeedOver2001, sum, inputCount);
						logEndTime = logEndTime.plusMinutes(5); // 時間を5分進める.
						logStartTime = logStartTime.plusMinutes(5); // 表示時間を5分進める.
						// ==すべてのカウントを初期化=============
						countOfResponseSpeedUnder500 = 0;  // ==
						countOfResponseSpeedUnder2000 = 0; // ==
						countOfResopnseSpeedOver2001 = 0;  // ==
						sum = 0;                           // ==
						inputCount = 0;                    // ==
						// =====================================
					}
					if (responseSpeed <= 500) {
						countOfResponseSpeedUnder500++;
					} else if (responseSpeed <= 2000) {
						countOfResponseSpeedUnder2000++;
					} else if (responseSpeed >= 2001) {
						countOfResopnseSpeedOver2001++;
					}
					sum += responseSpeed;
					inputCount++;
					finalTime = inputDate;
				} else {
					resultToConsole(logStartTime, countOfResponseSpeedUnder500, countOfResponseSpeedUnder2000,
							countOfResopnseSpeedOver2001, sum, inputCount);
					if (finalTime.isBefore(LocalDateTime.of(2012, 4, 7, 23, 55))) {
						resultToConsole(LocalTime.of(23, 55), 0, 0, 0, 0, 1);
					}
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		long endTime = System.currentTimeMillis();
		System.out.println("処理時間：" + (endTime-startTime));
		
	}

	/**
	 * コンソール出力を2回しているので、メソッドに切り出した.
	 * 
	 * @param logStartTime                  表示する時間
	 * @param countOfResponseSpeedUnder500  500ms以下
	 * @param countOfResponseSpeedUnder2000 2000ms以下
	 * @param countOfResopnseSpeedOver2001  2001ms以上
	 * @param sum                           合計
	 * @param inputCount                    回数
	 */
	private void resultToConsole(LocalTime logStartTime, int countOfResponseSpeedUnder500,
			int countOfResponseSpeedUnder2000, int countOfResopnseSpeedOver2001, int sum, int inputCount) {
		System.out.println(logStartTime.format(DateTimeFormatter.ofPattern("HH:mm")) + " |   "
				+ countOfResponseSpeedUnder500 + "       | " + countOfResponseSpeedUnder2000 + "           | "
				+ countOfResopnseSpeedOver2001 + "         | " + (sum / inputCount) + "            | ");
		System.out.println("---------------------------------------------------------------");
	}

}
