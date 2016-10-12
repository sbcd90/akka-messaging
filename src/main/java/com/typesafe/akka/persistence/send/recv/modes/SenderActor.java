package com.typesafe.akka.persistence.send.recv.modes;

import akka.actor.ActorSelection;
import akka.actor.UntypedActor;

import java.util.ArrayList;
import java.util.List;

public class SenderActor extends UntypedActor {
  private ActorSelection receiverActor;

  @Override
  public void preStart() {
    receiverActor = context().actorSelection(
    "akka.tcp://ReceiverSystem@127.0.0.1:5150/user/receiver");
    receiverActor.tell("Hi", getSelf());
    receiverActor.tell(
      new SingleItemData<String>("hi"), getSelf());
    receiverActor.tell(
      new AskStoreSingleItemData<String>("Hello"), getSelf());

    List<String> listData = new ArrayList<>();
    listData.add("Hello");
    listData.add("World");

    receiverActor.tell(
      new IterableData<String>(listData), getSelf());

    byte[] data = "Hello World".getBytes();
    receiverActor.tell(
      new ByteBufferData(data), getSelf());
  }

  @Override
  public void onReceive(Object msg) {
    if (msg instanceof Ack) {
      System.out.println("Acknowledgement received");
    } else {
      System.out.println("No acknowledgement");
    }
  }
}