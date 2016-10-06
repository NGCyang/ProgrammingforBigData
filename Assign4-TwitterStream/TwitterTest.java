import java.io.*;
import twitter4j.*;
import twitter4j.conf.*;


public class TwitterTest {
    public static void main(String[] args) throws  IOException, TwitterException{
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("EToVG9i6VKaxHYNcu9Dfq0CRP")
                .setOAuthConsumerSecret("AF1eKN3QFlRlq0YSo366Tpf7JT34QVarv34nivH2gtdmM0od20")
                .setOAuthAccessToken("300031403-3F8p4hRxOjXX3Lgj7SXsHj8n2YURfWmGgStLbSYw")
                .setOAuthAccessTokenSecret("opCp9elI6aGMB06EXA9Xc9H5mOjHONHKGOK0G2hwCK2TA");


        File file = new File("twitterstreaming.txt");

        PrintWriter writer = new PrintWriter(file);

        TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();
        StatusListener listener = new StatusListener() {
            @Override
            public void onStatus(Status status) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
                writer.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }

            @Override
            public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
                //System.out.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
                writer.println("Got a status deletion notice id:" + statusDeletionNotice.getStatusId());
            }

            @Override
            public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
                //System.out.println("Got track limitation notice:" + numberOfLimitedStatuses);
                writer.println("Got track limitation notice:" + numberOfLimitedStatuses);
            }

            @Override
            public void onScrubGeo(long userId, long upToStatusId) {
                //System.out.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
                writer.println("Got scrub_geo event userId:" + userId + " upToStatusId:" + upToStatusId);
            }

            @Override
            public void onStallWarning(StallWarning warning) {
                //System.out.println("Got stall warning:" + warning);
                writer.println("Got stall warning:" + warning);
            }

            @Override
            public void onException(Exception ex) {
                ex.printStackTrace();
            }
        };

        twitterStream.addListener(listener);
        twitterStream.sample();
    }
}
