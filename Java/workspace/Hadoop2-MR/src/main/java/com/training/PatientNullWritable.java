package main.java.com.training;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class PatientNullWritable {

	public static class PatientMapper extends Mapper<LongWritable, Text, NullWritable, IntWritable> 
	{
		
		// setup , map, run,cleanup

		public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException 
		{
			
			String line = value.toString();
			String[] elements = line.split(",");
			Text tx = new Text(elements[2]);
			
			int i = Integer.parseInt(elements[4]);
			IntWritable it = new IntWritable(i);
			context.write(NullWritable.get(), it);
		}
	}



public static class PatientReducer extends Reducer<Text, IntWritable, NullWritable, IntWritable> 
{
		
	public void reduce(NullWritable key, Iterable<IntWritable> values,
				Context context) throws IOException, InterruptedException 
	{
		
		int sum = 0;
		for (IntWritable val : values) 
		{
			sum += val.get();
		}
		context.write(NullWritable.get() , new IntWritable(sum));
	}
}

public static void main(String args[]) throws IOException, ClassNotFoundException,
        InterruptedException {
	
	Job job = Job.getInstance();

    // Input and Output formats
    job.setInputFormatClass(TextInputFormat.class);
    job.setOutputFormatClass(TextOutputFormat.class);

    // Mapper,Reducer and Invoker classes
    job.setMapperClass(PatientMapper.class);
    job.setReducerClass(PatientReducer.class);
    job.setJarByClass(PatientNullWritable.class);

    // set input and output path details, Output path should not exist.
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));

    // set output key and value types
    job.setOutputKeyClass(NullWritable.class);
    job.setOutputValueClass(IntWritable.class);

    job.setMapOutputKeyClass(NullWritable.class);
    job.setMapOutputValueClass(IntWritable.class);

    job.waitForCompletion(true);

}
}