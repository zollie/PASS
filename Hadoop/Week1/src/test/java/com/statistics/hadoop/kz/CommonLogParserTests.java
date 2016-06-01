package com.statistics.hadoop.kz;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class CommonLogParserTests {

	@Test
	public void test() {
		String path = Thread.currentThread().getContextClassLoader().getResource("apache.log").getPath();
		Set<CommonLogTuple> tuples = CommonLogParser.parseLog(path);
		
		CommonLogTuple t1 = tuples.iterator().next();
		
		System.out.println(t1);
		
		Assert.assertEquals(724846, tuples.size());
	}
}
