import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.io.NullWritable;

public class PageRankExtra {
    public static void main(String[] args) throws Exception {
        if (args.length != 4) {
          System.err.println("Usage: PageRankExtra <input path> <output1> <output1> <output3>");
          System.exit(-1);
        }

        // Job1
        Job job1 = new Job(); 
        job1.setJarByClass(PageRankExtra.class); 
        job1.setJobName("PageRankExtra1");

        FileInputFormat.addInputPath(job1, new Path(args[0])); 
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));

        job1.setMapperClass(PageRankMapper.class);
        job1.setReducerClass(PageRankReducer.class);

        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(Text.class);

        job1.setOutputKeyClass(NullWritable.class);
        job1.setOutputValueClass(Text.class);

        job1.waitForCompletion(true);

        // Job 2
        Job job2 = new Job(); 
        job2.setJarByClass(PageRankExtra.class); 
        job2.setJobName("PageRankExtra2");

        FileInputFormat.addInputPath(job2, new Path(args[1])); 
        FileOutputFormat.setOutputPath(job2, new Path(args[2]));

        job2.setMapperClass(PageRankMapper.class);
        job2.setReducerClass(PageRankReducer.class);

        job2.setMapOutputKeyClass(Text.class);
        job2.setMapOutputValueClass(Text.class);
        job2.setOutputKeyClass(NullWritable.class);
        job2.setOutputValueClass(Text.class);

        job2.waitForCompletion(true);

        // Job 3
        Job job3 = new Job(); 
        job3.setJarByClass(PageRankExtra.class); 
        job3.setJobName("PageRankExtra3");

        FileInputFormat.addInputPath(job3, new Path(args[2])); 
        FileOutputFormat.setOutputPath(job3, new Path(args[3]));

        job3.setMapperClass(PageRankMapper.class);
        job3.setReducerClass(PageRankReducer.class);

        job3.setMapOutputKeyClass(Text.class);
        job3.setMapOutputValueClass(Text.class);
        job3.setOutputKeyClass(NullWritable.class);
        job3.setOutputValueClass(Text.class);

        System.exit(job3.waitForCompletion(true) ? 0 : 1);

    }
}
