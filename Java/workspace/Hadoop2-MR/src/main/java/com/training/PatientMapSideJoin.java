package main.java.com.training;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.StringUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class PatientMapSideJoin extends Configured implements Tool
{		  
	  public static class MapperLarge extends Mapper<LongWritable,Text,Text,IntWritable> {
	    
		  ArrayList<String> PatientSmall = new ArrayList<String>();
		    
		    @Override
		    protected void setup(Context ctx) throws IOException, InterruptedException
		    {
		      Path[] cachedFiles = DistributedCache.getLocalCacheFiles(ctx.getConfiguration());
		      if (cachedFiles != null && cachedFiles.length > 0) 
		      {
		        BufferedReader reader = new BufferedReader(new FileReader(cachedFiles[0].toString()));
		        String line = null;
		        try {
		          while ((line = reader.readLine()) != null) 
		          {
		            PatientSmall.add(line);
		          }
		        } finally {
		          reader.close();
		        }
		      }
		    }
		    
		    @Override
		    protected void map(LongWritable key, Text value, Context ctx)  throws IOException, InterruptedException {
		    	
		    	
		      String[] cols = value.toString().split(",");
		      Text tx = new Text(cols[2]);
		      int i = Integer.parseInt(cols[4]);
			  IntWritable it = new IntWritable(i);
		      
		      
		      for( String drug : PatientSmall)
		      {
		    
		    	if(drug.equalsIgnoreCase(cols[2])) 
		    	{
		        ctx.write(tx, it);
		    	}
		      }
		  }
	  }
		  public static class Reducer1 extends
		      Reducer<Text,IntWritable,Text,IntWritable> {
		    
		    @Override
		    protected void reduce(Text key, Iterable<IntWritable> values, 
		        Context ctx) throws IOException, InterruptedException {
		      int sum = 0;
		      // then sum up all entry values for the given row
		      for (IntWritable value : values) {
		        sum = sum + value.get();
		        
		      }
		      ctx.write(key, new IntWritable(sum));
		    }
		  }
		  
		 // @Override
		  public int run(String[] args) throws Exception 
		  {
		    Configuration conf = getConf();
		    Job job = new Job(conf, "map Side Join");
		    
		    DistributedCache.addCacheFile(new Path(args[1]).toUri(),job.getConfiguration());
		    FileInputFormat.addInputPath(job, new Path(args[0]));
		    FileOutputFormat.setOutputPath(job, new Path(args[2]));
		    job.setJarByClass(PatientMapSideJoin.class);
		    job.setMapperClass(MapperLarge.class);
		    job.setReducerClass(Reducer1.class);
		    job.setInputFormatClass(TextInputFormat.class);
		    job.setMapOutputKeyClass(Text.class);
		    job.setMapOutputValueClass(IntWritable.class);
		    job.waitForCompletion(true);
		    
		    return 0;
		  }

	public static void main(String[] args) throws Exception 
	{
	    if (args.length != 3) {
	    System.out.println("Usage: inputfile1 joinfile output");
	    System.exit(-1);
	    }
	   int res = ToolRunner.run(new Configuration(), new PatientMapSideJoin(), args);
	   System.exit(res);
	}

}