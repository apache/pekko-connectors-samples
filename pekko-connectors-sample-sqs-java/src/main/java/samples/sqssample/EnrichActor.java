package samples.sqssample;

import org.apache.pekko.actor.typed.ActorRef;
import org.apache.pekko.actor.typed.Behavior;
import org.apache.pekko.actor.typed.javadsl.AbstractBehavior;
import org.apache.pekko.actor.typed.javadsl.ActorContext;
import org.apache.pekko.actor.typed.javadsl.Behaviors;
import org.apache.pekko.actor.typed.javadsl.Receive;

final class EnrichActor extends AbstractBehavior<EnrichActor.Enrich> {

  public static class Enrich {
    final int value;
    final ActorRef<Enriched> replyTo;
    public Enrich(int value, ActorRef<Enriched> replyTo) {
      this.value = value;
      this.replyTo = replyTo;
    }
  }

  public static class Enriched {
    final String data;
    Enriched(String data) {
      this.data = data;
    }

    @Override
    public String toString() {
      return "ActorResponseMsg(" + data + ")";
    }
  }

  public static Behavior<Enrich> create() {
    return Behaviors.setup(EnrichActor::new);
  }

  private EnrichActor(ActorContext<Enrich> context) {
    super(context);
  }

  @Override
  public Receive<Enrich> createReceive() {
    return newReceiveBuilder()
        .onMessage(Enrich.class, message -> {
          getContext().getLog().debug("actor received '{}'", message);
          message.replyTo.tell(new Enriched(message.toString()));
          return this;
        }).build();
  }

}
