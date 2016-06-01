package com.statistics.hadoop.kz.week3.q1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DataOutputBuffer;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileSplit;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

/**
 * Created by zollie on 4/20/14.
 */
public class DocWordRecordReader extends RecordReader<LongWritable, Text> {
    private long end;
    private boolean stillInChunk = true;

    private LongWritable key = new LongWritable();
    private Text value = new Text();

    private FSDataInputStream fsin;
    private DataOutputBuffer buffer = new DataOutputBuffer();


    private byte[] startTag = "=====".getBytes();

    /**
     * Called once at initialization.
     *
     * @param inputSplit   the split that defines the range of records to read
     * @param context the information about the task
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @Override
    public void initialize(InputSplit inputSplit, TaskAttemptContext context) throws IOException, InterruptedException {
        FileSplit split = (FileSplit)inputSplit;
        Configuration conf = context.getConfiguration();
        Path path = split.getPath();
        FileSystem fs = path.getFileSystem(conf);

        fsin = fs.open(path);
        long start = split.getStart();

        end = split.getStart() + split.getLength();
        fsin.seek(start);

        if (start != 0) {
            readUntilMatch(startTag, false);
        }
    }

    /**
     * Read the next key, value pair.
     *
     * @return true if a key/value pair was read
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @Override
    public boolean nextKeyValue() throws IOException, InterruptedException {
        if (!stillInChunk) return false;

        key = new LongWritable(getDocId());

        boolean status = readUntilMatch(startTag, true);

        value = new Text();
        value.set(buffer.getData(), 0, buffer.getLength());
        buffer.reset();

        // status is true as long as we're still within the
        // chunk we got (i.e., fsin.getPos() < end). If we've
        // read beyond the chunk it will be false
        if (!status) {
            stillInChunk = false;
        }

        return true;
    }

    /**
     * Get the current key
     *
     * @return the current key or null if there is no current key
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @Override
    public LongWritable getCurrentKey() throws IOException, InterruptedException {
        return key;
    }

    /**
     * Get the current value.
     *
     * @return the object that was read
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @Override
    public Text getCurrentValue() throws IOException, InterruptedException {
        return value;
    }

    /**
     * The current progress of the record reader through its data.
     *
     * @return a number between 0.0 and 1.0 that is the fraction of the data read
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @Override
    public float getProgress() throws IOException, InterruptedException {
        return 0;
    }

    /**
     * Close the record reader.
     */
    @Override
    public void close() throws IOException {
        fsin.close();
    }

    /**
     * Get docId
     *
     * @return
     * @throws IOException
     */
    private int getDocId() throws IOException {
        buffer.reset();
        while (true) {
            int b = fsin.read();
            buffer.write(b);
            if (b == -1) break;
            if (b == '\n') break;
        }

        String line = new String(buffer.getData());
        buffer.reset();

        line.replaceAll("=", "");
        line.replaceAll(" ", "");

        return Integer.parseInt(line);
    }

    /**
     *
     * @param match
     * @param withinBlock
     * @return
     * @throws IOException
     */
    private boolean readUntilMatch(byte[] match, boolean withinBlock) throws IOException {
        int i = 0;
        while (true) {
            int b = fsin.read();
            if (b == -1) return false;
            if (withinBlock)
                buffer.write(b);
            if (b == match[i]) {
                i++;
                if (i >= match.length) {
                    return fsin.getPos() < end;
                }
            } else i = 0;
        }
    }
}
