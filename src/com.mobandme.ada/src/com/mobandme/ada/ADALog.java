package com.mobandme.ada;

import android.util.Log;

/**
 * @author Jc Miñarro (josecarlos.minarro@gmail.com)
 */

public class ADALog {

	private static boolean debugMode;

	public static void enableDebugMode(boolean debugMode) {
		ADALog.debugMode = debugMode;
	}


	public static void i(String tag, String message) {
		if (debugMode) {
			Log.i(tag, message);
		}
	}

	public static void i(String tag, String message, Throwable tr) {
		if (debugMode) {
			Log.i(tag, message, tr);
		}
	}

	public static void e(String tag, String message) {
		if (debugMode) {
			Log.e(tag, message);
		}
	}

	public static void e(String tag, String message, Throwable tr) {
		if (debugMode) {
			Log.e(tag, message, tr);
		}
	}

	public static void d(String tag, String message) {
		if (debugMode) {
			Log.d(tag, message);
		}
	}

	public static void d(String tag, String message, Throwable tr) {
		if (debugMode) {
			Log.d(tag, message, tr);
		}
	}

	public static void v(String tag, String message) {
		if (debugMode) {
			Log.v(tag, message);
		}
	}

	public static void v(String tag, String message, Throwable tr) {
		if (debugMode) {
			Log.v(tag, message, tr);
		}
	}

	public static void w(String tag, String message) {
		if (debugMode) {
			Log.w(tag, message);
		}
	}

	public static void w(String tag, String message, Throwable tr) {
		if (debugMode) {
			Log.w(tag, message, tr);
		}
	}

	public static void wtf(String tag, String message) {
		if (debugMode) {
			Log.wtf(tag, message);
		}
	}

	public static void wtf(String tag, String message, Throwable tr) {
		if (debugMode) {
			Log.wtf(tag, message, tr);
		}
	}

	public static void w(String tag, Throwable tr) {
		if (debugMode) {
			Log.w(tag, tr);
		}
	}

	public static void wtf(String tag, Throwable tr) {
		if (debugMode) {
			Log.wtf(tag, tr);
		}
	}

}
