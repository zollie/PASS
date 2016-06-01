package com.statistics.hadoop.kz;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class ShakespeareQuestionTest {
	
	/**
	 * This outputs:
	 * 
	 * 	a = 4.3425784
	 *	b = 4.9313917
	 *	c = 7.1712894
	 *	d = 3.4001226
	 *	e = 3.4812539
	 *	f = 2.9625077
	 *	g = 6.1712894
	 *	h = 5.3703136
	 *	i = 2.4250154
	 *	j = 5.3703136
	 *	k = 4.685157
	 *	l = 4.685157
	 *	m = 6.185157
	 *	n = 3.9250154
	 *	o = 3.3703134
	 *	p = 4.4812536
	 *	q = 5.4022627
	 *	r = 5.740627
	 *	s = 1.9812539
	 *	t = 4.8703136
	 *	u = 6.872409
	 *	v = 3.9001226
	 *	w = 4.4812536
	 *	x = 3.100235
	 *	y = 2.9250154
	 *	z = 3.149403
	 *
	 * @throws Exception
	 */
	@Test
	public void answer() throws Exception {
	String path = Thread.currentThread().getContextClassLoader().getResource("shakespeare.txt").getPath();
	File file = new File(path);
	
	float a = 0;
	float b = 0;
	float c = 0;
	float d = 0;
	float e = 0;
	float f = 0;
	float g = 0;
	float h = 0;
	float i = 0;
	float j = 0;
	float k = 0;
	float l = 0;
	float m = 0;
	float n = 0;
	float o = 0;
	float p = 0;
	float q = 0;
	float r = 0;
	float s = 0;
	float t = 0;
	float u = 0;
	float v = 0;
	float w = 0;
	float x = 0;
	float y = 0;
	float z = 0;	
	
	BufferedReader br = null;

	try {
		br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] tuple = line.split("\t");
			
			if(tuple.length < 2) continue;
			
			String[] words = tuple[1].split("\\W");
			
			
			for(int _i=0;_i<words.length;_i++) {
				// words
				String word = words[_i];
				
				if(word == null || word.length() == 0) 
					continue;
				
				word = word.toLowerCase();
				
				switch(word.charAt(0)) {
					case 'a': a = (a + word.length()) / 2; break;
					case 'b': b = (a + word.length()) / 2; break;
					case 'c': c = (a + word.length()) / 2; break;
					case 'd': d = (a + word.length()) / 2; break;
					case 'e': e = (a + word.length()) / 2; break;
					case 'f': f = (a + word.length()) / 2; break;
					case 'g': g = (a + word.length()) / 2; break;
					case 'h': h = (a + word.length()) / 2; break;
					case 'i': i = (a + word.length()) / 2; break;
					case 'j': j = (a + word.length()) / 2; break;
					case 'k': k = (a + word.length()) / 2; break;
					case 'l': l = (a + word.length()) / 2; break;
					case 'm': m = (a + word.length()) / 2; break;
					case 'n': n = (a + word.length()) / 2; break;
					case 'o': o = (a + word.length()) / 2; break;
					case 'p': p = (a + word.length()) / 2; break;
					case 'q': q = (a + word.length()) / 2; break;
					case 'r': r = (a + word.length()) / 2; break;
					case 's': s = (a + word.length()) / 2; break;
					case 't': t = (a + word.length()) / 2; break;
					case 'u': u = (a + word.length()) / 2; break;
					case 'v': v = (a + word.length()) / 2; break;
					case 'w': w = (a + word.length()) / 2; break;
					case 'x': x = (a + word.length()) / 2; break;
					case 'y': y = (a + word.length()) / 2; break;
					case 'z': z = (a + word.length()) / 2; break;
				}
			}			
		}
	} catch(IOException _e) {
		throw new Error(_e);
	} finally {
		try {
			if(br != null) br.close();
		} catch(IOException e2) {}		
	}
	
	System.out.println("a = "+a);
	System.out.println("b = "+b);
	System.out.println("c = "+c);
	System.out.println("d = "+d);
	System.out.println("e = "+e);
	System.out.println("f = "+f);
	System.out.println("g = "+g);
	System.out.println("h = "+h);
	System.out.println("i = "+i);
	System.out.println("j = "+j);
	System.out.println("k = "+k);
	System.out.println("l = "+l);
	System.out.println("m = "+m);
	System.out.println("n = "+n);
	System.out.println("o = "+o);
	System.out.println("p = "+p);
	System.out.println("q = "+q);
	System.out.println("r = "+r);
	System.out.println("s = "+s);
	System.out.println("t = "+t);
	System.out.println("u = "+u);
	System.out.println("v = "+v);
	System.out.println("w = "+w);
	System.out.println("x = "+x);
	System.out.println("y = "+y);
	System.out.println("z = "+z);
	
	}	
}
