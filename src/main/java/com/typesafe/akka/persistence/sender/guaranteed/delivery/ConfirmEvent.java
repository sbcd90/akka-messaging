package com.typesafe.akka.persistence.sender.guaranteed.delivery;

public class ConfirmEvent extends Event {
  private int deliveryId;

  public ConfirmEvent(int deliveryId) {
    this.deliveryId = deliveryId;
  }

  public void setDeliveryId(int deliveryId) {
    this.deliveryId = deliveryId;
  }

  public int getDeliveryId() {
    return deliveryId;
  }

  @Override
  public String getDescription() {
    return "Confirm Event triggered";
  }
}