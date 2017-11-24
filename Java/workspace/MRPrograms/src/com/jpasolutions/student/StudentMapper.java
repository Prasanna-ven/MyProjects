package com.jpasolutions.student;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StudentMapper extends Mapper<LongWritable,Text,Text,LongWritable>{
	
	public void map(LongWritable key,Text value,Context context) throws IOException,InterruptedException{
		
		String a[] = value.toString().split(" ");
		Text outputKey = new Text(a[0]);
		LongWritable outputValue = new LongWritable(Long.parseLong(a[2]));
		context.write(outputKey,outputValue);
	}
}
