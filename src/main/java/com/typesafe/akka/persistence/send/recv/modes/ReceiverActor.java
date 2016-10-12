package com.typesafe.akka.persistence.send.recv.modes;

import akka.actor.UntypedActor;

public class ReceiverActor extends UntypedActor {

  @Override
  public void onReceive(Object msg) {
    if (msg instanceof SingleItemData) {
      System.out.println(((SingleItemData) msg).getItem());
    } else if (msg instanceof AskStoreSingleItemData) {
      System.out.println(((AskStoreSingleItemData) msg).getItem());
      getSender().tell(
        new Ack(), getSelf());
    } else if (msg instanceof IterableData) {
      System.out.println(((IterableData) msg).getItems());
    } else if (msg instanceof ByteBufferData) {
      System.out.println(((ByteBufferData) msg).getBytes().length);
    }
  }
}