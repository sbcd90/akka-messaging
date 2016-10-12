package com.typesafe.akka.persistence.sender.exactly.once.delivery.handling;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.akka.persistence.sender.guaranteed.delivery.AtleastOnceDeliveryActor;
import com.typesafe.akka.persistence.sender.guaranteed.delivery.ExternalRequest;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class ExactlyOnceDeliveryApp {

  private Config getExactlyOnceDeliveryConfig() {
    String configFile = getClass().getClassLoader()
      .getResource("persistent_actor.conf").getFile();
    Config config = ConfigFactory.parseFile(new File(configFile));
    return config;
  }

  public static void main(String[] args) throws InterruptedException {
    ExactlyOnceDeliveryApp app = new ExactlyOnceDeliveryApp();
    Config config = app.getExactlyOnceDeliveryConfig();

    ActorSystem system = ActorSystem.create(
      "persistent-exactly-once-delivery-app", config);

    ActorRef factActor = system.actorOf(Props.create(ExactlyOnceFactActor.class));

    ActorRef deliveryActor = system.actorOf(
    Props.create(AtleastOnceDeliveryActor.class, factActor.path()));

    system.scheduler().schedule(Duration.create(0, TimeUnit.SECONDS),
      Duration.create(5, TimeUnit.SECONDS),
      factActor,
      "print",
      system.dispatcher(),
      ActorRef.noSender());

    for (int i = 1; i <= 9999; i++) {
      deliveryActor.tell(new ExternalRequest(i), ActorRef.noSender());
    }

    Thread.sleep(10000);
    system.shutdown();
    system.awaitTermination();
  }
}