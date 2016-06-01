package com.statistics.hadoop.kz.week3.q2;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * 1. The number of flights per airport
 * 2. The mean arrival totalDelay per airport
 * 3. The arrival totalDelay standard deviation per airport
 * 4. The minimum arrival totalDelay per airport
 * 5. The maximum arrival totalDelay per airport
 *
 * Created by zollie on 4/19/14.
 */
public class OnTimeStatWritable implements WritableComparable<OnTimeStatWritable> {
    private String airport = "   ";
    private int count;
    private int totalDelay;
    private double meanDelay;
    private double sigma;
    private double minDelay;
    private double maxDelay;


    @Override
    public int compareTo(OnTimeStatWritable o) {
        if(getTotalDelay() < o.getTotalDelay()) return -1;
        if(getTotalDelay() < o.getTotalDelay()) return 1;
        return 0;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeChars(airport);
        out.writeInt(count);
        out.writeInt(totalDelay);
        out.writeDouble(meanDelay);
        out.writeDouble(sigma);
        out.writeDouble(minDelay);
        out.writeDouble(maxDelay);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        char a = in.readChar();
        char b = in.readChar();
        char c = in.readChar();

        airport = new String(new char[]{a,b,c});
        count = in.readInt();
        totalDelay = in.readInt();
        meanDelay = in.readDouble();
        sigma = in.readDouble();
        minDelay = in.readDouble();
        maxDelay = in.readDouble();
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotalDelay() {
        return totalDelay;
    }

    public void setTotalDelay(int totalDelay) {
        this.totalDelay = totalDelay;
    }

    public double getMeanDelay() {
        return meanDelay;
    }

    public void setMeanDelay(double meanDelay) {
        this.meanDelay = meanDelay;
    }

    public double getSigma() {
        return sigma;
    }

    public void setSigma(double sigma) {
        this.sigma = sigma;
    }

    public double getMinDelay() {
        return minDelay;
    }

    public void setMinDelay(double minDelay) {
        this.minDelay = minDelay;
    }

    public double getMaxDelay() {
        return maxDelay;
    }

    public void setMaxDelay(double maxDelay) {
        this.maxDelay = maxDelay;
    }

    @Override
    public int hashCode() {
        return airport.hashCode() + totalDelay;
    }

    @Override
    public boolean equals(Object obj) {
        OnTimeStatWritable other = (OnTimeStatWritable)obj;
        if(other == null) return false;
        return other.getAirport() == airport && other.getTotalDelay() == totalDelay;
    }
}