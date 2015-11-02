package com.virtusa.bookstore.server.resource.book;

import com.virtusa.bookstore.common.book.Book;
import com.virtusa.bookstore.server.db.Books;
import org.restlet.data.Status;
import org.restlet.ext.xml.DomRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Delete;
import org.restlet.resource.Get;
import org.restlet.resource.Put;
import org.restlet.resource.ServerResource;

/**
 * Created by hp on 10/31/2015.
 */
public class BookResource extends ServerResource {
    @Get
    public Representation getContact() throws Exception {
        DomRepresentation result = new DomRepresentation();
        String bookISBN = (String) this.getRequestAttributes().get("bookISBN");
        Book book = Books.getBookInstance().getBook(bookISBN);

        if (book == null) {
            getResponse().setStatus(Status.CLIENT_ERROR_NOT_ACCEPTABLE);
        } else {
            result.setDocument(book.bookToXml());
        }
        return result;
    }

    @Put
    public Representation putContact(Representation representation) throws Exception {
        DomRepresentation domRepresentation = new DomRepresentation(representation);
        Book book = new Book(domRepresentation.getDocument());
        Books.getBookInstance().addBook(book);
        return null;
    }

    @Delete
    public Representation deleteBook() {
        String bookISBN = (String) this.getRequestAttributes().get("bookISBN");
        Books.getBookInstance().removeBook(bookISBN);
        return null;
    }

}
