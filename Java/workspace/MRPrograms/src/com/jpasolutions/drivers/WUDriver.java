package com.jpasolutions.drivers;

import com.jpasolutions.mapreduce.WUMapper;
import com.jpasolutions.mapreduce.WUReducer;
import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

import java.io.IOException;


public class WUDriver {

	public static void main(String args[])
            throws IOException, ClassNotFoundException,
            InterruptedException {
        Job job = Job.getInstance();

        // Input and Output formats
        job.setInputFormatClass(TextInputFormat.class);

        job.setOutputFormatClass(TextOutputFormat.class);
        job.setJobName("Simple Word  Program");
        //LazyOutputFormat.setOutputFormatClass(job,TextOutputFormat.class);

        // Mapper,Reducer and Invoker classes
        job.setMapperClass(WUMapper.class);
        job.setReducerClass(WUReducer.class);
        job.setJarByClass(WUDriver.class);


        // set input and output path details, Output path should not exist.
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // set output key and value types
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        //job.getConfiguration().set("today","VERY_HIGH");
        // submit the job and wait for the completion
        job.waitForCompletion(true);


    }

}
