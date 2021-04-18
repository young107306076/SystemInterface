package edu.nccu.aps.util;

public class CommonUtil {
	public static boolean checkNotEmptyOrNull(Object object) {
		boolean tCheck = false;

		if (object != null && !"".equals(object)) {
			tCheck = true;
		}

		return tCheck;
	}
}