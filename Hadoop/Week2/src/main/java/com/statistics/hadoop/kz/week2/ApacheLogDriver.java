package com.statistics.hadoop.kz.week2;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by zollie on 4/13/14.
 */
public class ApacheLogDriver extends Configured implements Tool {
        @Override
        public int run(String[] args) throws Exception {
            Job job = new Job(getConf());
            job.setJarByClass(getClass());
            job.setJobName(getClass().getSimpleName());

            FileInputFormat.addInputPath(job, new Path("apache.log"));
            FileOutputFormat.setOutputPath(job, new Path("apache.out"));

            job.setMapperClass(ApacheLogMapper.class);
            job.setReducerClass(ApacheLogReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(IntWritable.class);

            return job.waitForCompletion(true) ? 0 : 1;
        }

        public static void main(String[] args) throws Exception {
            int rc = ToolRunner.run(new ApacheLogDriver(), args);
            System.exit(rc);
        }
    }