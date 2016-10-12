package com.typesafe.akka.persistence.sender.guaranteed.delivery;

import java.io.Serializable;
import java.util.Random;

public abstract class Event
  implements Serializable {
  private String id;

  public Event() {
    this.id = "Event-" + new Random(100);
  }

  public String getId() {
    return id;
  }

  public abstract String getDescription();
}