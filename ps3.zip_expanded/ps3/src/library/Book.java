package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Book is an immutable type representing an edition of a book -- not the physical object, 
 * but the combination of words and pictures that make up a book.  Each book is uniquely
 * identified by its title, author list, and publication year.  Alphabetic case and author 
 * order are significant, so a book written by "Fred" is different than a book written by "FRED".
 */
public class Book {
    private final String title;
    private final List<String> authors;
    private final int year;
    // TODO: rep
    
    // TODO: rep invariant
    //  authors: Alphabetic case and author order are significant, 
    //  authors must have at least one name, and each name must contain 
    //  at least one non-space character.
    //  title must contain at least one non-space character.
    //  year >= 0
    // TODO: abstraction function
    //  Each book is uniquely identified by its title, author list, and publication year.  
    //  Alphabetic case and author order are significant, so a book written by "Fred" is different than a book written by "FRED".
    // TODO: safety from rep exposure argument
    //  All fields are private;
    //  author and text are Strings, authors is a mutable List containing immutable string, 
    //  but constructor authors & getAuthors() makes a defensive copy of the List it returns, and all other parameters 
    //  and return values are immutable String or void, so are guaranteed immutable;
    
    /**
     * Make a Book.
     * @param title Title of the book. Must contain at least one non-space character.
     * @param authors Names of the authors of the book.  Must have at least one name, and each name must contain 
     * at least one non-space character.
     * @param year Year when this edition was published in the conventional (Common Era) calendar.  Must be nonnegative. 
     */
    public Book(String title, List<String> authors, int year) {
//        throw new RuntimeException("not implemented yet");
        this.title = title;
        this.authors = new ArrayList<String>(authors);
        this.year = year;
    }
    
    // assert the rep invariant
    private void checkRep() {
//        throw new RuntimeException("not implemented yet");
        List<String> authorsOne = new ArrayList();
        List<String> authorsTwo = new ArrayList();
        authorsOne.add("FRED");
        authorsOne.add("fred");
        authorsTwo.add("fred");
        authorsTwo.add("FRED");

        Book BookOne = new Book("abc", authorsOne, 2000);
        Book BookTwo = new Book("abc", authorsTwo, 2000);

        assert 1 <= title.length() && authors.size() >= 1;
        assert authorsOne.get(0).equals(authorsOne.get(1)) == false;
        // check >=1  non-space character of each title, author
        // year >= 0
        
    }
    
    /**
     * @return the title of this book
     */
    public String getTitle() {
//        throw new RuntimeException("not implemented yet");
        return title;
    }
    
    /**
     * @return the authors of this book
     */
    public List<String> getAuthors() {
//        throw new RuntimeException("not implemented yet");
        List<String> getCopyOfAuthors = new ArrayList<String>();
        for (String author : authors ) {
            getCopyOfAuthors.add(author);
        }
//        for (int i = 0; i < authors.size(); i++) {
//            getCopyOfAuthors.add(authors.get(i));
//        }
        return getCopyOfAuthors;
    }

    /**
     * @return the year that this book was published
     */
    public int getYear() {
//        throw new RuntimeException("not implemented yet");
        return year;
    }

    /**
     * @return human-readable representation of this book that includes its title,
     *    authors, and publication year
     */
    public String toString() {
//        throw new RuntimeException("not implemented yet");
        return "this book: " + title + " written by: " + authors.toString() + " in " + year; 
    }

    // uncomment the following methods if you need to implement equals and hashCode,
    // or delete them if you don't
     @Override
     public boolean equals(Object that) {
//         throw new RuntimeException("not implemented yet");
         if (this == that) {
             return true;
         }
         if (that == null || !(that instanceof Book)) {
             return false;
         } 
         
         Book obj = (Book) that;
         return obj.title.equals(title) && obj.authors.equals(authors)
                 && obj.year == year;         
     }
     
     @Override
     public int hashCode() {
//         throw new RuntimeException("not implemented yet");
         int result = title.hashCode();
         int result = result * 31 + authors.hashCode();
         int result = result * 31 + Integer.hashCode(year);
         return result;
     }



    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */

}
