package com.typesafe.akka.persistence.receiver.with.failures;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.akka.persistence.receiver.Command;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class PersistentFailureActorApp {
  private Config getPersistentFailureActorConfig() {
    String configFile = getClass().getClassLoader()
      .getResource("persistent_actor.conf").getFile();
    Config config = ConfigFactory.parseFile(new File(configFile));
    return config;
  }

  public static void main(String[] args) throws InterruptedException {
    PersistentFailureActorApp app = new PersistentFailureActorApp();
    Config config = app.getPersistentFailureActorConfig();

    ActorSystem system = ActorSystem.create("persistent-failure-actor-app", config);
    final ActorRef persistentFailureActor = system.actorOf(Props.create(PersistentFailureActor.class), "persistentFailureActor");

    persistentFailureActor.tell(new Command("foo"), ActorRef.noSender());
    persistentFailureActor.tell("print", ActorRef.noSender());
//    persistentFailureActor.tell("snap", ActorRef.noSender());
    persistentFailureActor.tell("boom", ActorRef.noSender());
    persistentFailureActor.tell("print", ActorRef.noSender());
    persistentFailureActor.tell(new Command("b"), ActorRef.noSender());
    persistentFailureActor.tell("print", ActorRef.noSender());
    persistentFailureActor.tell(new Command("c"), ActorRef.noSender());
    persistentFailureActor.tell("print", ActorRef.noSender());

    Thread.sleep(10000);
    system.terminate();
  }
}