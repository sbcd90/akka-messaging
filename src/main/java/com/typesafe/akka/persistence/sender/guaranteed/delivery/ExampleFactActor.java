package com.typesafe.akka.persistence.sender.guaranteed.delivery;

import akka.actor.UntypedActor;

public class ExampleFactActor extends UntypedActor {
  private int count = 0;

  public void onReceive(Object msg) throws Exception {
    if (msg instanceof CountCommand) {
      CountCommand command = (CountCommand) msg;
      int updateAmount = command.getUpdateAmount();
      int deliveryId = command.getDeliveryId();
//      System.out.println(deliveryId);

      count += updateAmount;

      getSender().tell(new Confirm(deliveryId), getSelf());
    } else if (msg.equals("print")) {
      System.out.println(count);
//      Thread.sleep(5000);
    }
  }
}