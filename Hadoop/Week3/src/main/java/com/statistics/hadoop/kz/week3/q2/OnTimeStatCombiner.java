package com.statistics.hadoop.kz.week3.q2;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by zollie on 4/13/14.
 */
public class OnTimeStatCombiner extends Reducer<Text, LongWritable, Text, OnTimeStatWritable> {
    /**
     *
     * @param key
     * @param values
     * @param context
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    public void reduce(Text key, Iterable<OnTimeStatWritable> values, Context context) throws IOException, InterruptedException {
        OnTimeStatWritable vout = new OnTimeStatWritable();

        for(OnTimeStatWritable v : values) {
            vout.setCount(vout.getCount()+v.getCount());
            vout.setTotalDelay(vout.getTotalDelay() + v.getTotalDelay());
            vout.setSigma(vout.getSigma() + v.getSigma());

            if (vout.getMinDelay() == 0 || v.getTotalDelay() < vout.getMinDelay())
                vout.setMinDelay(v.getMinDelay());

            if (v.getMaxDelay() > vout.getMaxDelay())
                vout.setMaxDelay(v.getMaxDelay());
        }

        context.write(key, vout);
    }

}