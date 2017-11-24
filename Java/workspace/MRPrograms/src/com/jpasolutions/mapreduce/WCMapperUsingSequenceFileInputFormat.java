package com.jpasolutions.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Sivakumar Annamalai on 9/8/15.
 */
public class WCMapperUsingSequenceFileInputFormat extends
        Mapper<Text,LongWritable,Text,LongWritable> {

    public void map(Text key,LongWritable value,Context context)
            throws IOException, InterruptedException {
        context.write(key,value);
    }
}
