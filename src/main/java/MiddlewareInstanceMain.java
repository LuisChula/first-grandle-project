import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.Scanner;

public class MiddlewareInstanceMain {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ActorSystem system = ActorSystem.create("system");
        ActorRef sub1 = system.actorOf(Props.create(Subscriber.class), "subscriber1");

        ActorRef publisher = system.actorOf(Props.create(Publisher.class), "publisher");

        // after a while the subscriptions are replicated
        publisher.tell("hello", null);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String c;
        do {
            c = myObj.nextLine();  // Read user input
            publisher.tell(c, null);
        } while(!c.equals("e"));
    }

}
