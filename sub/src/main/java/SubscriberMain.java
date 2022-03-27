import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;

public class SubscriberMain {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String systemName = "PubSub";
        ActorSystem system1 = ActorSystem.create(systemName);
        Cluster cluster1 = Cluster.get(system1);
        cluster1.join(cluster1.selfAddress());

        /*System.out.println(cluster1.selfAddress().protocol());
        System.out.println(cluster1.selfAddress().system());
        System.out.println(cluster1.selfAddress().host());
        System.out.println(cluster1.selfAddress().port());*/

        ActorRef sub1 = system1.actorOf(Props.create(Subscriber.class), "subscriber1");

    }

}
