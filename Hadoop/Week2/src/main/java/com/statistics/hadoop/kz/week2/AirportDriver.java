package com.statistics.hadoop.kz.week2;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by zollie on 4/13/14.
 */
public class AirportDriver extends Configured implements Tool {
        @Override
        public int run(String[] args) throws Exception {
            Job job = new Job(getConf());
            job.setJarByClass(getClass());
            job.setJobName(getClass().getSimpleName());

            FileInputFormat.addInputPath(job, new Path("airport_stats.txt"));
            FileOutputFormat.setOutputPath(job, new Path("airport.out"));

            job.setMapperClass(AirportMapper.class);
            job.setReducerClass(AirportReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(LongWritable.class);

            return job.waitForCompletion(true) ? 0 : 1;
        }

        public static void main(String[] args) throws Exception {
            int rc = ToolRunner.run(new AirportDriver(), args);
            System.exit(rc);
        }
    }