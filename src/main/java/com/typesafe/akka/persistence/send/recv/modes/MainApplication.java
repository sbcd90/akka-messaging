package com.typesafe.akka.persistence.send.recv.modes;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class MainApplication {
  private Config getSenderConfig() {
    String configFile = getClass().getClassLoader()
      .getResource("sender_actor.conf").getFile();
    Config config = ConfigFactory.parseFile(new File(configFile));
    return config;
  }

  private Config getReceiverConfig() {
    String configFile = getClass().getClassLoader()
      .getResource("receiver_actor.conf").getFile();
    Config config = ConfigFactory.parseFile(new File(configFile));
    return config;
  }

  public static void main(String[] args) {
    MainApplication app = new MainApplication();

    Config senderConfig = app.getSenderConfig();
    Config receiverConfig = app.getReceiverConfig();

    ActorSystem senderSystem = ActorSystem.create("SenderSystem", senderConfig);
    ActorRef senderActor = senderSystem.actorOf(Props.create(SenderActor.class), "sender");
    System.out.println("Sender actor is ready");

    ActorSystem receiverSystem = ActorSystem.create("ReceiverSystem", receiverConfig);
    ActorRef receiverActor = receiverSystem.actorOf(Props.create(ReceiverActor.class), "receiver");
    System.out.println("Receiver actor is ready");
  }
}