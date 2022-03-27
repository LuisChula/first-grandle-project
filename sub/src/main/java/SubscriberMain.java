import akka.actor.ActorSystem;
import akka.actor.Props;

public class SubscriberMain {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ActorSystem system = ActorSystem.create("system");
        system.actorOf(Props.create(Subscriber.class), "subscriber1");
    }

}
