package com.statistics.hadoop.kz;

import java.util.Set;

public class ApacheLogQuestion {
	
	/**
	 * This outputs:
	 * 
	 *	Jan = 43570
	 *	Feb = 71954
	 *	Mar = 99879
	 *	Apr = 64940
	 *	May = 63818
	 *	Jun = 53651
	 *	Jly = 54795
	 *	Aug = 66160
	 *	Sep = 89006
	 *	Oct = 46007
	 *	Nov = 41221
	 *	Dec = 29845
	 */
	public void answer() {
		String path = Thread.currentThread().getContextClassLoader().getResource("apache.log").getPath();
		Set<CommonLogTuple> tuples = CommonLogParser.parseLog(path);
		
		int jan = 0;
		int feb = 0;
		int mar = 0;
		int apr = 0;
		int may = 0;
		int jun = 0;
		int jly = 0;
		int aug = 0;
		int sep = 0;
		int oct = 0;
		int nov = 0;
		int dec = 0;
		
		for(CommonLogTuple t : tuples) {
			if(t.getDateTime() == null) continue;
			int month = t.getDateTime().getMonthOfYear();
			
			switch(month) {
				case 1: jan++; break;
				case 2: feb++; break;
				case 3: mar++; break;
				case 4: apr++; break;
				case 5: may++; break;
				case 6: jun++; break;
				case 7: jly++; break;
				case 8: aug++; break;
				case 9: sep++; break;
				case 10: oct++; break;
				case 11: nov++; break;
				case 12: dec++; break;
			}
		}
		
		System.out.println("Jan = "+jan);
		System.out.println("Feb = "+feb);
		System.out.println("Mar = "+mar);
		System.out.println("Apr = "+apr);
		System.out.println("May = "+may);
		System.out.println("Jun = "+jun);
		System.out.println("Jly = "+jly);
		System.out.println("Aug = "+aug);
		System.out.println("Sep = "+sep);
		System.out.println("Oct = "+oct);
		System.out.println("Nov = "+nov);
		System.out.println("Dec = "+dec);
	}
}
