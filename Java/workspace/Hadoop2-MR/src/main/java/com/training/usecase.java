package main.java.com.training;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

public class usecase {
	
	public static class PatientMapper extends Mapper<LongWritable, Text, Text, Text> 
	{
		StringBuilder sb = new StringBuilder();
		// setup , map, run,cleanup

		public void map(LongWritable key, Text value, Context context)
		throws IOException, InterruptedException 
		{
			
			String line = value.toString();
			String[] elements = line.split(",");
			Text tx = new Text(elements[2]);
			
			for(String str : elements)
			{
				sb.append(str);
				sb.append(",");
			}
			
			Text it = new Text(sb.toString());
			context.write(tx, it);
		
		}
	}
	
	public static class PatientReducer extends Reducer<Text, Text, Text, Text> 
	{
			
		public void reduce(Text key, Iterable<Text> values,
					Context context) throws IOException, InterruptedException 
		{

			for(Text str : values)
			{
				context.write(key ,str);
			}
			
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

		job.setJarByClass(usecase.class); // class conmtains mapper and
												// reducer class
		
		job.setMapOutputKeyClass(Text.class); // map output key class
		job.setMapOutputValueClass(Text.class);// map output value class
		job.setOutputKeyClass(Text.class); // output key type in reducer
		job.setOutputValueClass(Text.class);// output value type in
													// reducer

		job.setMapperClass(PatientMapper.class);
		job.setReducerClass(PatientReducer.class);
		job.setNumReduceTasks(1);
		job.setInputFormatClass(TextInputFormat.class); // default -- inputkey
														
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
		//job.submit();
		
		

	}

}
