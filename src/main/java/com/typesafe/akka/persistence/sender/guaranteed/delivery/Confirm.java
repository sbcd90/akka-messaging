package com.typesafe.akka.persistence.sender.guaranteed.delivery;

public class Confirm {
  private int deliveryId;

  public Confirm(int deliveryId) {
    this.deliveryId = deliveryId;
  }

  public void setDeliveryId(int deliveryId) {
    this.deliveryId = deliveryId;
  }

  public int getDeliveryId() {
    return deliveryId;
  }
}