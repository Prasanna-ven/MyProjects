package com.jpasolutions.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by Sivakumar Annamalai on 24/4/15.
 */
public class WCMapperTabBased extends Mapper<LongWritable,Text,Text,LongWritable> {
    LongWritable one = new LongWritable(1);

    public void map(LongWritable key,Text value,Context ctx) throws IOException, InterruptedException {
        // Split the record delimited by Space, this will return array of words
        String words[] = value.toString().split("\\t");

        // Iterate the words in the array and sent to the reducer by writing on ctx object
        for(String word:words){
            ctx.write(new Text(word),one);
        }
    }
}
