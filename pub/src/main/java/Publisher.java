import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;

public class Publisher extends AbstractActor {

    // activate the extension
    ActorRef mediator = DistributedPubSub.get(getContext().system()).mediator();

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        String.class,
                        in -> {
                            String out = in.toUpperCase();
                            System.out.println(out);
                            System.out.println(mediator);
                            mediator.tell(new DistributedPubSubMediator.Publish("content", out), getSelf());
                        })
                .build();
    }
}