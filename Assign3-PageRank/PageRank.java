import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat; 
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import org.apache.hadoop.io.NullWritable;

public class PageRank {
    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
          System.err.println("Usage: PageRank <input path> <output path>");
          System.exit(-1);
        }

        // Job1
        Job job1 = new Job(); 
        job1.setJarByClass(PageRank.class); 
        job1.setJobName("PageRank");

        FileInputFormat.addInputPath(job1, new Path(args[0])); 
        FileOutputFormat.setOutputPath(job1, new Path(args[1]));

        job1.setMapperClass(PageRankMapper.class);
        job1.setReducerClass(PageRankReducer.class);

        job1.setMapOutputKeyClass(Text.class);
        job1.setMapOutputValueClass(Text.class);

        job1.setOutputKeyClass(NullWritable.class);
        job1.setOutputValueClass(Text.class);

        System.exit(job1.waitForCompletion(true) ? 0 : 1);

    }
}
