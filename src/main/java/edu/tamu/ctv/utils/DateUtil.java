package edu.tamu.ctv.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtil
{
	public static Date GetCurrentDate()
	{
		return Calendar.getInstance().getTime();
	}
}
