package com.statistics.hadoop.kz.week3.q2;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by zollie on 4/13/14.
 */
public class OnTimeStatDriver extends Configured implements Tool {
        public static final Text COUNT = new Text("count");
        public static final Text TOTAL = new Text("total");
        public static final Text MEAN = new Text("mean");
        public static final Text SIGMA = new Text("sigma");
        public static final Text MIN = new Text("min");
        public static final Text MAX = new Text("max");

    @Override
        public int run(String[] args) throws Exception {
            Job job = new Job(getConf());
            job.setJarByClass(getClass());
            job.setJobName(getClass().getSimpleName());

//            job.getConfiguration().set("mapred.job.tracker", "local");
//            job.getConfiguration().set("fs.default.name", "local");

            FileInputFormat.addInputPath(job, new Path("ontime_flights.tsv"));
            FileOutputFormat.setOutputPath(job, new Path("ontime_flights.out"));
//            job.addCacheFile(new Path("stopwords.txt").toUri());

            job.setMapperClass(OnTimeStatMapper.class);
            job.setCombinerClass(OnTimeStatCombiner.class);
            job.setReducerClass(OnTimeStatReducer.class);

            job.setOutputKeyClass(Text.class);
            job.setOutputValueClass(OnTimeStatWritable.class);

            return job.waitForCompletion(true) ? 0 : 1;
        }

        public static void main(String[] args) throws Exception {
            int rc = ToolRunner.run(new OnTimeStatDriver(), args);
            System.exit(rc);
        }
    }