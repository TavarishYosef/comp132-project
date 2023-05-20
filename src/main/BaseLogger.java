package main;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * A class for logging info and errors to files
 * @author Yusuf
 *
 */
public class BaseLogger {
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[E MMM dd HH:mm:ss z yyyy]");

	private String logFile;
	private String type;
	/**
	 * Creates a new {@link BaseLogger} object
	 * @param logFile File to be written
	 */
	private BaseLogger(String logFile) {
		this.logFile = logFile;
	}
	/**
	 * Used for logging info logs
	 * @return A {@link BaseLogger} instance for info logging
	 */
	public static BaseLogger info() {
		BaseLogger baseLogger = new BaseLogger("application_log.txt");
		baseLogger.type = "[INFO] ";
		return baseLogger;
	}
	/**
	 * Used for logging error logs
	 * @return A {@link BaseLogger} instance for errors logging
	 */
	public static BaseLogger error() {
		BaseLogger baseLogger = new BaseLogger("application_error.txt");
		baseLogger.type = "[ERROR] ";
		return baseLogger;
	}
	/**
	 * Logs the message to the log file
	 * @param message Message to be written
	 */
	public void log(String message) {
		String formattedLog = DATE_FORMAT.format(new Date()) + type + message;

		try (FileWriter writer = new FileWriter(logFile, true)) {
			writer.write(formattedLog);
		} catch (IOException e) {
			System.err.println("Error writing to log file: " + logFile);
		}
	}
}
