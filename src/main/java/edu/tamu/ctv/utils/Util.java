package edu.tamu.ctv.utils;

import java.util.ArrayList;
import java.util.List;

public class Util
{
	public static Integer[] convertArrayToInt(String[] strArray)
	{
		Integer[] numbers = new Integer[strArray.length];
		for(int i = 0;i < strArray.length;i++)
		{
		   numbers[i] = Integer.parseInt(strArray[i]);
		}
		return numbers;
	}
	
	public static List<Integer> convertArrayToInt(List<String> strArray)
	{
		List<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0;i < strArray.size();i++)
		{
			numbers.add(Integer.parseInt(strArray.get(i)));
		}
		return numbers;
	}
}
