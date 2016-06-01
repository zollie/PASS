package com.statistics.hadoop.kz.week3.q1;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;
import org.apache.hadoop.mapreduce.lib.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by zollie on 4/13/14.
 */
public class RuetersDriver extends Configured implements Tool {
        public static final Text COUNT = new Text("count");
        public static final Text TOTAL = new Text("total");
        public static final Text MEAN = new Text("mean");
        public static final Text SIGMA = new Text("sigma");
        public static final Text MIN = new Text("min");
        public static final Text MAX = new Text("max");

        @Override
        public int run(String[] args) throws Exception {
            JobControl jc = new JobControl("rueters");

            jc.addJob(getJob1());
            jc.addJob(getJob2());
            jc.addJob(getJob3());

            return 0;
        }

        public static void main(String[] args) throws Exception {
            int rc =  ToolRunner.run(new RuetersDriver(), args);
            System.exit(rc);
        }


        private ControlledJob getJob1() throws Exception {
            ControlledJob job = new ControlledJob(getConf());
            job.getJob().setJarByClass(getClass());
            job.setJobName(getClass().getSimpleName());

            FileInputFormat.addInputPath(job.getJob(), new Path("reuters.txt"));
            FileOutputFormat.setOutputPath(job.getJob(), new Path("wordfreq.out"));
            job.getJob().addCacheFile(new Path("stopwords.txt").toUri());

            job.getJob().setMapperClass(WordFreqMapper.class);
            job.getJob().setReducerClass(WordFreqReducer.class);

            job.getJob().setOutputKeyClass(ArrayWritable.class);
            job.getJob().setOutputValueClass(LongWritable.class);

            return job;
        }

        private ControlledJob getJob2() throws Exception {
            ControlledJob job = new ControlledJob(getConf());
            job.getJob().setJarByClass(getClass());
            job.setJobName(getClass().getSimpleName());

            FileInputFormat.addInputPath(job.getJob(), new Path("wordfreq.out"));
            FileOutputFormat.setOutputPath(job.getJob(), new Path("wordcount.out"));

            job.getJob().setMapperClass(WordCountMapper.class);
            job.getJob().setReducerClass(WordCountReducer.class);

            job.getJob().setOutputKeyClass(ArrayWritable.class);
            job.getJob().setOutputValueClass(LongWritable.class);

            return job;
        }

    private ControlledJob getJob3() throws Exception {
        ControlledJob job = new ControlledJob(getConf());
        job.getJob().setJarByClass(getClass());
        job.setJobName(getClass().getSimpleName());

        FileInputFormat.addInputPath(job.getJob(), new Path("wordcount.out"));
        FileOutputFormat.setOutputPath(job.getJob(), new Path("idf.out"));

        job.getJob().setMapperClass(IDFMapper.class);
        job.getJob().setReducerClass(IDFReducer.class);

        job.getJob().setOutputKeyClass(ArrayWritable.class);
        job.getJob().setOutputValueClass(LongWritable.class);

        return job;
    }
    }

