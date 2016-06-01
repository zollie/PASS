package com.statistics.hadoop.kz.week3.q2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 *
 * Created by zollie on 4/19/14
 */
public class OnTimeStatMapper extends Mapper<LongWritable, Text, Text, OnTimeStatWritable> {


    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(value == null) return;
        String line = value.toString();
        if(line == null) return;
        String[] columns = line.toString().split("\\t");
        if(columns.length < 16) return;
        if (columns[15].equals("") || columns[13].equals("")) return;

        String airport = columns[6];


        Text txt = new Text(airport);
        int delay;

        try {
            delay = Integer.parseInt(columns[15]);
        } catch(NumberFormatException e) { throw e; }

        // emit(key, (1, value, Math.pow(value, 2), value, value));
        OnTimeStatWritable v = new OnTimeStatWritable();
        v.setAirport(airport);
        v.setCount(1);
        v.setTotalDelay(delay);
        v.setSigma(Math.pow(delay, 2));
        v.setMaxDelay(delay);
        v.setMinDelay(delay);
        v.setMeanDelay(delay);

        context.write(txt, v);
    }

}
