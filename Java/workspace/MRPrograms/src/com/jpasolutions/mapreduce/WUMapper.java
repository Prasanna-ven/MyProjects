package com.jpasolutions.mapreduce;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WUMapper extends
Mapper<LongWritable,Text,Text,NullWritable> {

	LongWritable one = new LongWritable(1);	    

	    public void map(LongWritable key,Text value,Context context)
	            throws IOException, InterruptedException {
	        String words[] = value.toString().split(" ");
	        // Other way to iterate the words
	        for(String word:words){
	            Text outValue = new Text(word);
	            context.write(outValue,NullWritable.get());
	        }
	    }
}







