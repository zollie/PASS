package com.statistics.hadoop.kz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

public class CommonLogParser {
	private static Pattern p = Pattern.compile("([^ ]*) ([^ ]*) ([^ ]*) \\[([^]]*)\\] \"([^\"]*)\" ([^ ]*) ([^ ]*)");
	private static DateTimeFormatter dtFormat = DateTimeFormat.forPattern("dd/MMM/yyyy:H:mm:SS Z"); // 24/Oct/1994:13:46:45 -0600

	
	public static Set<CommonLogTuple> parseLog(String path) {
		return parseLog(new File(path));	
	}
	
	public static Set<CommonLogTuple> parseLog(File file) {
		Set<CommonLogTuple> tups = new HashSet<CommonLogTuple>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				Matcher m = p.matcher(line);
				if(m.matches()) {
					CommonLogTuple tup = new CommonLogTuple();
					int g = 1;
					tup.setHost(m.group(g));
					tup.setRfc931(m.group(++g));
					tup.setUsername(m.group(++g));
					tup.setDateTime(DateTime.parse(m.group(++g), dtFormat));
					tup.setRequest(m.group(++g));
					tup.setStatusCode(m.group(++g));
					int bc = 0;
					try {
						bc = Integer.parseInt(m.group(++g));
					}
					catch(NumberFormatException e) {}
					tup.setBytes(bc);
					tups.add(tup);
				}
			}
			return tups;
		} catch(IOException e) {
			throw new Error(e);
		} finally {
			try {
				if(br != null) br.close();
			} catch(IOException e2) {}		
		}
	}
}
