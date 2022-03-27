import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Address;
import akka.actor.Props;
import akka.cluster.Cluster;

import java.util.Scanner;

public class PublisherMain {

    public static void main(String[] args) {
        String systemName = "PubSub";
        ActorSystem system2 = ActorSystem.create(systemName);
        Cluster cluster2 = Cluster.get(system2);
        cluster2.join(new Address("akka", systemName, "127.0.0.1", 2551));

        ActorRef publisher = system2.actorOf(Props.create(Publisher.class), "publisher");
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
