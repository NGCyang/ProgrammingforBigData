import java.io.IOException;
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.util.*;

public class TweetsMapper
    extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value, Context context) 
        throws IOException, InterruptedException { 
            //String tweet = value.toString();
            String[] words = value.toString().toLowerCase().trim().split("\\W+");

            HashMap<String, Integer> result = new HashMap<String, Integer>();
            result.put("hackathon", 0);
            result.put("dec", 0);
            result.put("chicago", 0);
            result.put("java", 0);

            for (String word : words) {
                if (result.containsKey(word) && result.get(word) != 1)  {
                    
                    result.put(word, 1);
                    //context.wirte(new Text(word), new IntWritable(1));
                    
                }
            }

            for (String word : result.keySet()) {
                context.write(new Text(word), new IntWritable(result.get(word)));
            }

    }

}

