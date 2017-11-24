package main.java.com.training;

import java.io.IOException;

import main.java.com.training.PatientCounter.PatientMapper.USERCOUNTER;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class PatientCounter {
	
	
	
	public static class PatientMapper extends Mapper<LongWritable, Text, Text, IntWritable> 
	{
		enum USERCOUNTER {MALE}
		
		// setup , map, run,cleanup

		public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException 
		{
			
			String line = value.toString();
			String[] elements = line.split(",");
			Text tx = new Text(elements[2]);
			
			String Gender = elements[3];
						
			int i = Integer.parseInt(elements[4]);
			IntWritable it = new IntWritable(i);
			
			if(Gender.equalsIgnoreCase("Male"))
			{
				context.getCounter(USERCOUNTER.MALE).increment(1);
				context.write(tx, it);
			}
		
		}
	}
	
	public static class PatientReducer extends Reducer<Text, IntWritable, Text, IntWritable> 
	{
			
		public void reduce(Text key, Iterable<IntWritable> values,
					Context context) throws IOException, InterruptedException 
		{
			
			int sum = 0;
			for (IntWritable val : values) 
			{
				sum += val.get();
			}
			context.write(key , new IntWritable(sum));
		}
	}
		

	public static void main(String[] args) throws Exception  {
		
		if (args.length != 2) {
			System.err.println("Insufficient args");
			System.exit(-1);
		}
		Configuration conf = new Configuration();

		conf.set("mapred.job.tracker", "hdfs://localhost:50001");

		Job job = new Job(conf, "Drug Amount Spent");

		job.setJarByClass(PatientCounter.class); // class conmtains mapper and
												// reducer class
		
		job.setMapOutputKeyClass(Text.class); // map output key class
		job.setMapOutputValueClass(IntWritable.class);// map output value class
		job.setOutputKeyClass(Text.class); // output key type in reducer
		job.setOutputValueClass(IntWritable.class);// output value type in
													// reducer

		job.setMapperClass(PatientMapper.class);
		job.setReducerClass(PatientReducer.class);
		job.setNumReduceTasks(1);
		job.setInputFormatClass(TextInputFormat.class); // default -- inputkey
														
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
		
		// Retrieve the counter after the job gets over
        System.out.println("Counter Value is "+
          job.getCounters().findCounter(PatientMapper.USERCOUNTER.MALE).getValue());
         Counter mapcounter = job.getCounters().findCounter(USERCOUNTER.MALE);
      //   long male_count = mapcounter.getValue();
      //   long total_count= mapcounter.getValue() + 1000;
        System.out.println("Male count" + mapcounter);
      //   System.out.println("Total count"+total_count);
		
		

	}

}
