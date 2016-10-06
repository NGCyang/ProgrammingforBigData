import java.io.IOException;
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.DoubleWritable;
 
import org.apache.hadoop.mapreduce.Mapper;
import java.util.*;

public class PageRankMapper
    extends Mapper<LongWritable, Text, Text, Text> {

    @Override
    public void map(LongWritable key, Text value, Context context) 
        throws IOException, InterruptedException { 

            if (value == null) {
                return;
            }

            String[] page = value.toString().trim().split(" ");

            String currPage = page[0];
            Double initPR = Double.parseDouble(page[page.length-1]);

            int linkNum = page.length - 2;
            String links = "";



            for (int i = 1; i < page.length-1; i++) {
                links = links + page[i].trim() + " ";
                context.write(new Text(page[i].trim()), 
                    new Text(currPage + " " + initPR/linkNum));
            }

            context.write(new Text(currPage), 
                new Text(links + "-1.0"));
    }

}

