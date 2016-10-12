package com.typesafe.akka.persistence.receiver.without.failures;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.akka.persistence.receiver.Command;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class PersistentActorApp {
  private Config getPersistentActorConfig() {
    String configFile = getClass().getClassLoader()
      .getResource("persistent_actor.conf").getFile();
    Config config = ConfigFactory.parseFile(new File(configFile));
    return config;
  }

  public static void main(String[] args) throws InterruptedException {
    PersistentActorApp app = new PersistentActorApp();
    Config config = app.getPersistentActorConfig();

    ActorSystem system = ActorSystem.create("persistent-actor-app", config);
    ActorRef persistentActor = system.actorOf(Props.create(PersistentActor.class), "persistentActor");
    persistentActor.tell(new Command("foo"), ActorRef.noSender());
    persistentActor.tell("print", ActorRef.noSender());
    persistentActor.tell("snap", ActorRef.noSender());
    persistentActor.tell(new Command("bar"), ActorRef.noSender());
    persistentActor.tell(new Command("baz"), ActorRef.noSender());
    persistentActor.tell("snap", ActorRef.noSender());
    persistentActor.tell(new Command("buzz"), ActorRef.noSender());
    persistentActor.tell("print", ActorRef.noSender());
//    persistentActor.tell("snap", ActorRef.noSender());

    Thread.sleep(10000);
    system.terminate();
  }
}