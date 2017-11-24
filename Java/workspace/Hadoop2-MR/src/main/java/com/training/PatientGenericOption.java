package main.java.com.training;

import java.io.IOException;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileRecordReader;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;


public class PatientGenericOption extends Configured implements Tool{

	public static class PatientMap extends Mapper<LongWritable, Text, Text, IntWritable> {

	private String drugName;
	private Text outKey = new Text();
	private IntWritable outValue = new IntWritable();
	// setup , map, run, cleanup
	

	public void setup(Context context) throws IOException,InterruptedException 
	{
		System.out.println("Inside setup class");
	drugName = context.getConfiguration().get("drugName");
	System.out.println("after setting the drug" + drugName);
	}


	public void map(LongWritable key, Text value, Context context)	throws IOException, InterruptedException 
	{

		String line = value.toString();
		String[] elements = line.split(",");
		if (drugName.equalsIgnoreCase(elements[2])) 
		{
	
			outKey.set(elements[2]);
	
			int i = Integer.parseInt(elements[4]); 
			outValue.set(i);
			context.write(outKey, outValue); 
		}
	}
}

	public static class PatientReduce extends Reducer<Text, IntWritable, Text, IntWritable> {

		// setup, reduce, run, cleanup

		private IntWritable outValue = new IntWritable();

// innput - para [150,100]
		public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException 
		{
			int sum = 0;
			for (IntWritable val : values) {
				sum += val.get();
			}
			outValue.set(sum);
			context.write(key, outValue);
		}
}



	@Override
	public int run(String[] args) throws Exception {
	// TODO Auto-generated method stub
	
	Configuration conf = getConf();
	//conf.set("mapred.job.tracker", "localhost:50001");
	conf.set("drugName", args[2]); // setting up drug Name from Command Line
	Job job = new Job(conf, "Drug Amount Spent with conf");
	
	job.setJarByClass(PatientGenericOption.class); // class conmtains mapper and
												// reducer class
	job.setMapOutputKeyClass(Text.class); // map output key class
	job.setMapOutputValueClass(IntWritable.class);// map output value class
	job.setOutputKeyClass(Text.class); // output key type in reducer
	job.setOutputValueClass(IntWritable.class);// output value type in
												// reducer
	job.setMapperClass(PatientMap.class);
	job.setReducerClass(PatientReduce.class);
	job.setNumReduceTasks(1);
	job.setInputFormatClass(TextInputFormat.class); 
	job.setOutputFormatClass(TextOutputFormat.class);
	FileInputFormat.addInputPath(job, new Path(args[0])); // input Path
	FileOutputFormat.setOutputPath(job, new Path(args[1]));// output path
	
	// job.submit();
	job.waitForCompletion(true);
	
	
	return 0;
}

	public static void main(String[] args) throws Exception 
	{	
		System.out.println(args.toString());
		if (args.length < 2) {
		System.err.println("Insufficient args");
		System.exit(-1);
	}
		int res = ToolRunner.run(new Configuration(),new PatientGenericOption(), args);
		System.exit(res);
	}
}	
