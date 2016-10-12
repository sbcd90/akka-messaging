package com.typesafe.akka.persistence.sender.guaranteed.delivery;

import akka.actor.ActorPath;
import akka.japi.Function;
import akka.japi.Procedure;
import akka.persistence.UntypedPersistentActorWithAtLeastOnceDelivery;

public class AtleastOnceDeliveryActor
  extends UntypedPersistentActorWithAtLeastOnceDelivery {
  private final ActorPath destPath;

  public AtleastOnceDeliveryActor(ActorPath destPath) {
    this.destPath = destPath;
  }

  public String persistenceId() {
    return "persistent-actor-ref-1";
  }

  @Override
  public void onReceiveRecover(Object msg) throws Throwable {
    if (msg instanceof Event) {
      updateState((Event) msg);
    }
  }

  @Override
  public void onReceiveCommand(Object msg) throws Throwable {
    if (msg instanceof ExternalRequest) {
      persist(new CountEvent(((ExternalRequest) msg).getUpdateAmount()), new Procedure<CountEvent>() {
        @Override
        public void apply(CountEvent event) throws Exception {
          updateState(event);
        }
      });
    } else if (msg instanceof Confirm) {
      persist(new ConfirmEvent(((Confirm) msg).getDeliveryId()), new Procedure<ConfirmEvent>() {
        @Override
        public void apply(ConfirmEvent event) throws Exception {
          updateState(event);
        }
      });
    }
  }

  private void updateState(Event event) {
    if (event instanceof CountEvent) {
      deliver(destPath, new Function<Long, Object>() {
        @Override
        public Object apply(Long id) throws Exception {
          return new CountCommand(id.intValue(),
            ((CountEvent) event).getUpdateAmount());
        }
      });
    } else if (event instanceof ConfirmEvent) {
      Long deliveryId = new Long(((ConfirmEvent) event).getDeliveryId());
      confirmDelivery(deliveryId);
    }
  }
}