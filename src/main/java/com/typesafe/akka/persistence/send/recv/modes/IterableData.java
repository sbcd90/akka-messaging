package com.typesafe.akka.persistence.send.recv.modes;

import java.lang.Iterable;

public class IterableData<T> extends PacketData {
  private static final Long serialVersionUID = 1L;
  private Iterable<T> items;

  public IterableData(Iterable<T> items) {
    this.items = items;
  }

  public void setItems(Iterable<T> items) {
    this.items = items;
  }

  public Iterable<T> getItems() {
    return items;
  }
}