package com.virtusa.bookstore.server.db;

import com.virtusa.bookstore.common.book.Book;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by hp on 10/31/2015.
 */
public class Books {
    private Map<String, Book> booksMap = new ConcurrentHashMap<String, Book>();

    private static Books bookObject = null;

    private Books() {
    }

    public static Books getBookInstance() {
        if (bookObject == null) {
            bookObject = new Books();
        }
        return bookObject;
    }

    public synchronized void addBook(Book book) {
        booksMap.put(book.getBookISBN(), book);
    }

    public synchronized Book getBook(String bookISBN) {
        return booksMap.get(bookISBN);
    }

    public synchronized void removeBook(String bookISBN) {
        booksMap.remove(bookISBN);
    }

    public synchronized Collection<Book> getBooks() {
        return booksMap.values();
    }
}
