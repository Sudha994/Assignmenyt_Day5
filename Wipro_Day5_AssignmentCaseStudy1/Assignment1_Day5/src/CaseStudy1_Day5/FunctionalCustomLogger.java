package CaseStudy1_Day5;

import java.util.function.Consumer;
import java.util.function.Predicate;

@FunctionalInterface
interface LogFilter {
	boolean shouldLog(String message);
}

public class FunctionalCustomLogger {

	public static void main(String[] args) {

		//using custom functional interface
		LogFilter errorFilter = msg -> msg.startsWith("[ERROR]");
		logMessage("System started", errorFilter);
		logMessage("[ERROR] Disk full", errorFilter);

		//using Predicate(in-built method)
		Predicate<String> warningFilter = msg->msg.startsWith("[WARN");
		logMessage("[WARN] Low memory", warningFilter);

		//using Consumer for log output
		Consumer<String> consoleLogger = System.out::println;
		consoleLogger.accept("Logging to console");

	}
	private static void logMessage(String message, LogFilter filter) {
		if(filter.shouldLog(message)) {
			System.out.println(message);
		}
	}

		private static void logMessage(String message, Predicate<String> filter) {
			if(filter.test(message)) {
				System.out.println(message);
			}
		}

	}
