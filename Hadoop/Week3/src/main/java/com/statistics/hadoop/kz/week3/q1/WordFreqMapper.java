package com.statistics.hadoop.kz.week3.q1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * Created by zollie on 4/19/14
 */
public class WordFreqMapper extends Mapper<LongWritable, Text, ArrayWritable, LongWritable> {
    private Set<String> stopwords = new HashSet<String>() ;

    /**
     *
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        super.setup(context);

        Configuration conf = context.getConfiguration();
        String stpF = conf.get("stopwords.txt");
        if(stpF == null)
            return;
        File stp_file = new File(stpF);
        BufferedReader fis;
        try {
            fis = new BufferedReader(new FileReader(stp_file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not open stopwords file ",e);
        }
        String word;
        try {
            while((word =fis.readLine()) != null){
                stopwords.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("error while reading stopwords",e);
        }
    }

    /**
     * Called once for each key/value pair in the input split. Most applications
     * should override this, but the default is the identity function.
     */
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        if(value == null) return;

        String[] words = value.toString().split(" ");

        for(String w : words) {
            if(stopwords.contains(w)) return;
            ArrayWritable mw = new ArrayWritable(Text.class);
            Text docId = new Text(key.toString());
            Text val = new Text(w);
            mw.set(new Text[]{docId, val});
            context.write(mw, new LongWritable(1));
        }
    }

}
