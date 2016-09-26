import java.io.IOException;
import org.apache.hadoop.io.IntWritable; 
import org.apache.hadoop.io.LongWritable; 
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.NullWritable;

public class PageRankReducer 
	extends Reducer<Text, Text, NullWritable, Text> {

	@Override
	public void reduce(Text key, Iterable<Text> values, Context context)
		throws IOException, InterruptedException {

			double pr = 0.0;
			String[] linkList;
			String result = "";
			
			for (Text value : values) {
				String[] outvalue = value.toString().trim().split(" ");
				double last = Double.parseDouble(outvalue[outvalue.length - 1]);

				if (last < 0) {
					for(int i = 0; i <  outvalue.length - 1; i++) {
						result = result + outvalue[i].trim() + " ";
					}
				} else {
					pr += last;
				}
    		}



    		context.write(NullWritable.get(), 
    			new Text(key.toString() + " "+ result + pr));
	}
}
