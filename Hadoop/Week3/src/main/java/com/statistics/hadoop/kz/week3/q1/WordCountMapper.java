package com.statistics.hadoop.kz.week3.q1;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 *
 *
 * Created by zollie on 4/19/14
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, ArrayWritable> {


    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(value == null) return;

        String[] keyVals = value.toString().split(" ");


        Text docId = new Text(keyVals[0]);
        Text word = new Text(keyVals[1]);

        LongWritable tf = new LongWritable(Long.parseLong(keyVals[2]));
        ArrayWritable aw = new ArrayWritable(LongWritable.class);

        aw.set(new LongWritable[] { tf, new LongWritable(1) });

        context.write(word, aw);

    }

}
