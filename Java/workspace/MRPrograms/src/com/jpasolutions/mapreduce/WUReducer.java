package com.jpasolutions.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;

public class WUReducer extends Reducer<Text,NullWritable,Text,NullWritable>{
	
	public void reduce(Text key,NullWritable values,
            Context ctx) throws IOException, InterruptedException {
// initialize the variable sum
long sum=0;

// Iterate the list of values for the key and sum it


// In the end you got the word(as key) and corresponding count(as sum). Write to ctx object.
ctx.write(key,NullWritable.get());
}

}
