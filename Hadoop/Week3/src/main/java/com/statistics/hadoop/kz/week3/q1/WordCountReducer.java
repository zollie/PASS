package com.statistics.hadoop.kz.week3.q1;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by zollie on 4/13/14.
 */
public class WordCountReducer extends Reducer<ArrayWritable, Text, ArrayWritable, LongWritable> {
    /**
     *
     * @param key
     * @param values
     * @param context
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    public void reduce(ArrayWritable key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long total = 0;

        for(LongWritable v : values) {
            total += v.get();
        }

        context.write(key, new LongWritable(total));
    }
}