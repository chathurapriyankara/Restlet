package com.virtusa.bookstore.common.book;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by hp on 10/31/2015.
 */
public class Book {
    private String bookISBN;
    private String bookName;
    private String bookAuthor;

    private static final String BOOK_ISBN_ELEMENT_NAME = "book-isbn";
    private static final String BOOK_NAME_ELEMENT_NAME = "book-name";
    private static final String BOOK_AUTHOR_ELEMENT_NAME = "book-author";

    public Book(String bookName, String bookISBN, String bookAuthor) {
        this.bookAuthor = bookAuthor;
        this.bookISBN = bookISBN;
        this.bookName = bookName;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public Book(Document doc) {
        this.bookName = getElementContent(doc, BOOK_NAME_ELEMENT_NAME);
        this.bookISBN = getElementContent(doc, BOOK_ISBN_ELEMENT_NAME);
        this.bookAuthor = getElementContent(doc, BOOK_AUTHOR_ELEMENT_NAME);
    }

    private String getElementContent(Document doc, String elementName) {
        //Get first element matches with the element name from xml document object
        Element element = (Element) doc.getElementsByTagName(elementName).item(0);
        return element.getTextContent();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Book) && (this.bookISBN.equals(((Book) object).bookISBN));
    }

    @Override
    public int hashCode() {
        return bookISBN.hashCode();
    }

    public Document bookToXml() throws Exception {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Element rootElement = doc.createElement("book");
        doc.appendChild(rootElement);

        attachElement(doc, rootElement, BOOK_ISBN_ELEMENT_NAME, this.bookISBN);
        attachElement(doc, rootElement, BOOK_NAME_ELEMENT_NAME, this.bookName);
        attachElement(doc, rootElement, BOOK_AUTHOR_ELEMENT_NAME, this.bookAuthor);

        return doc;
    }

    private void attachElement(Document doc, Element parent, String childElementName, String childElementValue) {
        Element childElement = doc.createElement(childElementName);
        childElement.setTextContent(childElementValue);
        parent.appendChild(childElement);
    }

    @Override
    public String toString(){
        return String.format("[ISBN : %s Name: %s Author : %s]",this.bookISBN,this.bookName,this.bookAuthor);
    }
}
