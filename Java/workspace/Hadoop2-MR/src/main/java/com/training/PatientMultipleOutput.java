package main.java.com.training;

import java.io.IOException;

import main.java.com.training.PatientDriver.PatientMapper;
import main.java.com.training.PatientDriver.PatientReducer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.lib.MultipleTextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.LazyOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class PatientMultipleOutput {

	public static class PatientMapper extends Mapper<LongWritable, Text, Text, IntWritable> 
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
			context.write(tx, it);
		}
	}
	
	public static class PatientReducer extends Reducer<Text, IntWritable, Text, IntWritable> 
	{
		private MultipleOutputs<Text, IntWritable> mos;
		
		public void setup(Context context)
		{
			mos = new MultipleOutputs<Text, IntWritable>(context);
		}
			
		public void reduce(Text key, Iterable<IntWritable> values,
					Context context) throws IOException, InterruptedException 
		{
			
			int sum = 0;
			for (IntWritable val : values) 
			{
				sum += val.get();
			}
			//context.write(key , new IntWritable(sum));
			mos.write(key, new IntWritable(sum), key.toString());
			
		}
		
		public void cleanup(Context context) throws IOException, InterruptedException {
	        mos.close();
	    }
	}
	
	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException 
		{
        
		Configuration conf = new Configuration();

		conf.set("mapred.job.tracker", "hdfs://localhost:50001");

		Job job = new Job(conf, "Drug Amount Spent");

		job.setJarByClass(PatientMultipleOutput.class); // class conmtains mapper and
												// reducer class
		
		job.setMapOutputKeyClass(Text.class); // map output key class
		job.setMapOutputValueClass(IntWritable.class);// map output value class
		job.setOutputKeyClass(Text.class); // output key type in reducer
		job.setOutputValueClass(IntWritable.class);// output value type in
													// reducer

		job.setMapperClass(PatientMapper.class);
		job.setReducerClass(PatientReducer.class);
		//job.setNumReduceTasks(1);
		job.setInputFormatClass(TextInputFormat.class); // default -- inputkey
														
		MultipleOutputs.addNamedOutput(job, "text", TextOutputFormat.class,Text.class,IntWritable.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
		
		
    }
}