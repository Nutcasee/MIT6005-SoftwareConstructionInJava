package twitter;

import java.util.List;
import java.util.Set;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.Instant;
/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
//        throw new RuntimeException("not implemented");
        if (tweets == null) {
            throw new IllegalArgumentException("tweets is empty");
        }
        Instant start = Instant.MAX;
        Instant end = Instant.MIN;
        
        for (Tweet tweet : tweets) {
            if (tweet.getTimestamp().isBefore(start)) {
                start = tweet.getTimestamp();
            } else if (tweet.getTimestamp().isAfter(end)) {
                end = tweet.getTimestamp();
            }
            
        }        
        return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
//        throw new RuntimeException("not implemented");
//        if (tweets == null) {
//            throw new IllegalArgumentException("tweets is empty");
//        }
        
        Set<String> users = new HashSet<String>();
        for (Tweet tweet : tweets) {
            users.add(tweet.getAuthor());
        }
        
        Set<String> getUsers = new HashSet<String>();
        
        // (\w+(-))@(\w+(-*))+
        String regex = ".*([^\\w-]*)@[\\w-]+([^\\w-]*)"; //"(\\W&&[^-]*)@(\\w&&-*)$";
        Pattern pattern = Pattern.compile(regex);
        
        for (Tweet tweet : tweets) {
            String text = tweet.getText();
            
//            System.out.println("text is: " + text);
            
            String[] words = text.split(" ");            
//            System.out.println("words are: " + Arrays.toString(words));
            for (String word : words) {
                System.out.println("word IS: " + word);
                CharSequence charSeq = word;
                Matcher m = pattern.matcher(charSeq);
                
                if (m.matches()) {
                    String afterAt = word.split("@")[1];
                    System.out.println("afterAt IS: " + afterAt);
                    String refinedWord = afterAt.split("[^\\w-]")[0];
                    System.out.println("refinedWord IS: " + refinedWord);
                    for (String user : users) {                        
                        if (user.equalsIgnoreCase(refinedWord)) {
                            System.out.println("USER are: " + user);
                            getUsers.add(user);
                        }
                    }
                }             
            }           
        }
        return getUsers;
    }

    /* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
