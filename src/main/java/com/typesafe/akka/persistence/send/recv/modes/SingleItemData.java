package com.typesafe.akka.persistence.send.recv.modes;

public class SingleItemData<T> extends PacketData {
  private static final Long serialVersionUID = 1L;
  private T item;

  public SingleItemData(T item) {
    this.item = item;
  }

  public void setItem(T item) {
    this.item = item;
  }

  public T getItem() {
    return item;
  }
}