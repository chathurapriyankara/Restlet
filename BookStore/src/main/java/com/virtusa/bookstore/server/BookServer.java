package com.virtusa.bookstore.server;

import com.virtusa.bookstore.server.resource.book.BookResource;
import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;

/**
 * Created by hp on 10/31/2015.
 */
public class BookServer extends Application {
    public static void startServer(int port)throws Exception{
        Component component = new Component();
        component.getServers().add(Protocol.HTTP,port);
        Application application = new BookServer();

        String contextRoot = "/bookserver";
        component.getDefaultHost().attach(contextRoot,application);
        component.start();
    }

    public static void main(String[] args)throws Exception{
        startServer(8111);
    }

    @Override
    public Restlet createInboundRoot(){
        Router router = new Router(getContext());
        router.attach("/book/{bookISBN}", BookResource.class);
        return router;
    }
}
