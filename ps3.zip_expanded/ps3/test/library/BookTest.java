package library;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test suite for Book ADT.
 */
public class BookTest {

    /*
     * Testing strategy
     * ==================
     * check immutability of getAuthors list
     * check name of title, author which has >= 1 non-space character
     * check order and alphabetic case of authors
     * check the unique of book 
     * ...
     * TODO: your testing strategy for this ADT should go here.
     * Make sure you have partitions.
     */
    
    Book book0 = new Book("Book 0", Arrays.asList("X", "Y", "Z"), 1990);
    Book book1 = new Book("Book 1", Arrays.asList("Y", "X", "Z"), 1990);
    Book book2 = new Book("Book 2", Arrays.asList("Z", "XYZ"), 1990);
    
    // TODO: put JUnit @Test methods here that you developed from your testing strategy
    @Test
    public void testExampleTest() {
        Book book = new Book("This Test Is Just An Example", Arrays.asList("You Should", "Replace It", "With Your Own Tests"), 1990);
        assertEquals("This Test Is Just An Example", book.getTitle());
    }
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testImmutabilityOfGetAuthors() {
        List<String> checkImmuAuthors = book0.getAuthors();
        checkImmuAuthors.remove(0);
        assertNotEquals("The 2 lists now are different, about size, contain..", checkImmuAuthors.size(), book0.getAuthors().size());
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
