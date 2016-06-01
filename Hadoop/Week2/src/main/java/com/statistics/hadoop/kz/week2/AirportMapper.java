package com.statistics.hadoop.kz.week2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by zollie on 4/13/14.ß
 */
public class AirportMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
    private static final LongWritable ONE = new LongWritable(1);

    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    @SuppressWarnings("unchecked")
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(value == null) return;
        String line = value.toString();
        if(line == null) return;
        String[] columns = line.toString().split("\\t");
        if(columns.length == 0) return;
        String airport = columns[0];
        Text txt = new Text(airport);
        context.write(txt, ONE);
    }

}
