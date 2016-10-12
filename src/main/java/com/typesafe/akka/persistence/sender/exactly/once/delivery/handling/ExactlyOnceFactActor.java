package com.typesafe.akka.persistence.sender.exactly.once.delivery.handling;

import akka.actor.UntypedActor;
import com.typesafe.akka.persistence.sender.guaranteed.delivery.Confirm;
import com.typesafe.akka.persistence.sender.guaranteed.delivery.CountCommand;

public class ExactlyOnceFactActor extends UntypedActor {
  private int count = 0;
  public AmountBuffer buffer = new AmountBuffer();

  public void onReceive(Object msg) throws Exception {
    if (msg instanceof CountCommand) {
      CountCommand command = (CountCommand) msg;
      int updateAmount = command.getUpdateAmount();
      int deliveryId = command.getDeliveryId();

      if (!buffer.getDeliveryIds().contains(updateAmount)) {
        count += updateAmount;
        buffer.addAmount(updateAmount);
      } else {
        System.out.println("ignoring update Amount " + updateAmount);
      }

      getSender().tell(new Confirm(deliveryId), getSelf());
    } else if (msg.equals("print")) {
      System.out.println(count);
//      Thread.sleep(20000);
    }
  }
}