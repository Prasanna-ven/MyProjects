package main.java.com.training;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
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
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class PatientReduceJoin extends Configured implements Tool {

	public static class PatientMap extends	Mapper<LongWritable, Text, Text, Text> {

	private Text outKey = new Text();
	private Text outValue = new Text();
	
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		System.out.println("Inside Map Method");
		
		String line = value.toString();
		String[] elements = line.split(",");
	
		outKey.set(elements[2]); // join key is drug name - paracetamol
		outValue.set("P" + elements[4]);// P is used to denote this value is from patient mapper class P100
		System.out.println("Key"+ outKey.toString()+"Value" + outValue.toString());
		context.write(outKey, outValue);
	}
	}
	
	public static class DrugMap extends Mapper<LongWritable, Text, Text, Text> {
	
	private Text outKey = new Text();
	private Text outValue = new Text();
	
	public void map(LongWritable key, Text value, Context context)	throws IOException, InterruptedException {
		
		String line = value.toString();
		String[] elements = line.split(",");
		outKey.set(elements[2]);// join key is drug name paracetamol
		outValue.set("D" + elements[0]); // D is used to denote this value is ffrom drug mapper calss -- drug id  D01
		System.out.println("Key"+ outKey.toString()+"Value" + outValue.toString());
		context.write(outKey, outValue);
	}
}
	
	public static class JoinReduce extends 	Reducer<Text, Text, Text, IntWritable> {
	
	private Text outKey = new Text();
	private IntWritable outValue = new IntWritable();
	private Integer sum = 0;
	private ArrayList<Integer> listP = new ArrayList<Integer>();
	HashSet<String> hashSetD = new HashSet<String>();
	
	// input key - paracetamol  input values - [P100,D01,P60]
	public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	
		listP.clear();
		hashSetD.clear();
		sum = 0;
		
		// iterate through all our values
		
		for (Text val : values)
			if (val.charAt(0) == 'P') {
				listP.add(Integer.parseInt(val.toString().substring(1))); // remove P from P100 and add 100 to list
				System.out.println("Patient Map drug Name"+Integer.parseInt(val.toString().substring(1)) );
			} else if (val.charAt(0) == 'D') {
				hashSetD.add(val.toString().substring(1)); //remove D from D01 and 01 to set
				System.out.println("Drug Map Id value"+ val.toString().substring(1));
			}
		
		executeJoinLogic(context);
	}
	
			private void executeJoinLogic(Context context) throws IOException,InterruptedException {
				
				System.out.println(hashSetD.toString());
				System.out.println(listP.toString());
				
				if (!listP.isEmpty() && !hashSetD.isEmpty() && hashSetD.size() == 1) {
					System.out.println("condition satisfied");
					for (String D : hashSetD) {
						outKey.set(D);
						for (Integer a : listP) {
							sum += a;
						}
						outValue.set(sum);
					}
					context.write(outKey, outValue);// 01  160
				}
			
			}
	
	}
	
	public int run(String[] args) throws Exception {
	
	Configuration conf = new Configuration();

	
	Job job = new Job(conf, "Reduce Side Inner Join");

	job.setJarByClass(PatientReduceJoin.class);
	
	job.setMapOutputKeyClass(Text.class);
	job.setMapOutputValueClass(Text.class);
	
	job.setOutputKeyClass(Text.class);
	job.setOutputValueClass(IntWritable.class);
	
	
	System.out.println(args[0]);
	System.out.println(args[1]);
//patientinputfile
	MultipleInputs.addInputPath(job, new Path(args[0]),TextInputFormat.class, PatientMap.class);
//druginput file
	MultipleInputs.addInputPath(job, new Path(args[1]),	TextInputFormat.class, DrugMap.class);

	job.setReducerClass(JoinReduce.class);
	job.setNumReduceTasks(1);
//	job.setInputFormatClass(TextInputFormat.class);
	job.setOutputFormatClass(TextOutputFormat.class);

	// FileInputFormat.addInputPath(job, new Path("/patientrx_10.txt"));
	FileOutputFormat.setOutputPath(job, new Path(args[2]));

	job.waitForCompletion(true);
	
	return 0;
	}
	
	public static void main(String[] args) throws Exception {

		if (args.length != 3) {
			System.err.println("Insufficient args");
			System.exit(-1);
		}
	
		int res = ToolRunner.run(new Configuration(), new PatientReduceJoin(),args);
		System.exit(res);
	}
	
}
