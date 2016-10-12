package com.typesafe.akka.persistence.receiver;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TestState implements Serializable {
  private static final Long serialVersionUID = 1L;
  private final List<String> events;

  public TestState(List<String> events) {
    this.events = events;
  }

  public TestState() {
    this(new ArrayList<>());
  }

  public TestState copy() {
    return new TestState(events);
  }

  public void update(Event event) {
    events.add(event.getData());
  }

  public int size() {
    return events.size();
  }

  @Override
  public String toString() {
    return events.toString();
  }
}