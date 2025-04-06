package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * Make sure you have partitions.
     */
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T12:00:00Z");
    private static final Instant d4 = Instant.parse("2016-02-17T14:00:00Z");
    private static final Instant d5 = Instant.parse("2016-02-17T15:00:00Z");
    private static final Instant d6 = Instant.parse("2016-02-17T16:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);

    private static final Tweet tweet3 = new Tweet(3, "csad", "@ALyssa insensitive", d3);
    private static final Tweet tweet4 = new Tweet(4, "ddas", "\"Hello @eess, welcome to @alyssa!\"", d4);
    private static final Tweet tweet5 = new Tweet(5, "eess", "yes @ddas,  sdfjsdl @alyssa ", d5);
    private static final Tweet tweet6 = new Tweet(6, "fran", "not@alyssa sdlfsdf", d6);

    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test
    public void testGuessFollowsGraphNotEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet4, tweet5));
        
        assertTrue("expected not empty graph", followsGraph.size() == 2);
    }
    
//    @Test
//    public void testInfluencersEmpty() {
//        Map<String, Set<String>> followsGraph = new HashMap<>();
//        List<String> influencers = SocialNetwork.influencers(followsGraph);
//        
//        assertTrue("expected empty list", influencers.isEmpty());
//    }

    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */


    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
