package com.statistics.hadoop.kz.week2;

import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;
import org.joda.time.DateTime;

import java.io.IOException;

/**
 * Created by zollie on 4/13/14.ß
 */
public class ApacheLogMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private static final IntWritable ONE = new IntWritable(1);

    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    @SuppressWarnings("unchecked")
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        CommonLogTuple tup = CommonLogParser.parseLine(value.toString());
        if(tup == null) return;
        DateTime dt = tup.getDateTime();
        if(dt == null) return;
        IntWritable hour = new IntWritable(dt.getHourOfDay());
        Text lr = new Text(tup.getHost());
        if(lr == null) return;
        Text mr = new Text(""+":"+lr+hour);
        context.write(mr, ONE);
    }

}
