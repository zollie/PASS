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
public class IDFMapper extends Mapper<LongWritable, Text, ArrayWritable, LongWritable> {


    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(value == null) return;

        String[] keyVals = value.toString().split(" ");

        Text word = new Text(keyVals[0]);
        Text docId = new Text(keyVals[1]);

//        double idf = Math.log( )
//        ArrayWritable aw = new ArrayWritable(Text.class);
//        context.write(newKey, new LongWritable(1));
    }

}
