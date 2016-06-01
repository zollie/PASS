package com.statistics.hadoop.kz.week2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by zollie on 4/13/14.
 */
public class AirportReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    private LongWritable result = new LongWritable();

    public void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long avg = 0;
        long n = 0;
        for (LongWritable val : values) {
            avg += val.get();
            n++;
        }
        if(n > 0)
            avg = avg / n;

        result.set(avg);
        context.write(key, result);
    }

}