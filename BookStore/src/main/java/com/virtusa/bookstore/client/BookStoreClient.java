package com.virtusa.bookstore.client;

import com.virtusa.bookstore.common.book.Book;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.resource.ClientResource;

/**
 * Created by hp on 10/31/2015.
 */
public class BookStoreClient {
    public static void main(String[] args)throws Exception{
        ClientResource client = new ClientResource("http://localhost:8111/bookserver/book/123");
        Book book = new Book("abc","456","adam");
        DomRepresentation representation = new DomRepresentation();
        representation.setDocument(book.bookToXml());
        client.put(representation);


        DomRepresentation dr = new DomRepresentation(client.get());
        Book book1 = new Book(dr.getDocument());
        System.out.println("GET RESULT : "+book1.toString());
    }
}
