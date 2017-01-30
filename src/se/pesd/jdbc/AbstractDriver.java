package se.pesd.jdbc;

import java.io.IOException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public abstract class AbstractDriver implements java.sql.Driver {

	protected static Logger logger;

	static boolean DEBUG = false;
	static {
		logger = Logger.getLogger(AbstractDriver.class.getName());

		if (DEBUG) {
			try {

				FileHandler handler = new FileHandler("/Users/Admin/Desktop/se.pesd.jdbc.Driver.log", true);

				Formatter formatter = new SimpleFormatter();
				handler.setFormatter(formatter);
				handler.setLevel(Level.ALL);

				logger.addHandler(handler);

				logger.setLevel(Level.ALL);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public int getMajorVersion() {
		return 1;
	}

	@Override
	public int getMinorVersion() {
		return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return logger;
	}

}
