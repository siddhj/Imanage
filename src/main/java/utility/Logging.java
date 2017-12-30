package utility;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

public class Logging {
private static Logger logger;
public static FileAppender appender;
public static  FileAppender getAppender() {
	return appender;
}
public static void setAppender(FileAppender appender) {
	Logging.appender = appender;
}

}
