import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import java.util.Scanner;

public class PublisherMain {

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create("system");

        // somewhere else
        ActorRef publisher = system.actorOf(Props.create(Publisher.class), "publisher");

        // wait a bit for the subscription to be gossiped
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // after a while the subscriptions are replicated
        publisher.tell("hello", null);

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String c;
        do {
            System.out.print("> ");
            c = myObj.nextLine();  // Read user input
            System.out.println(c);
            publisher.tell(c, null);
        } while(!c.equals("e"));


    }

}
