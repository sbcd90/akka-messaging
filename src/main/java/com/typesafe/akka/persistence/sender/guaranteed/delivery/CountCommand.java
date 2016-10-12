package com.typesafe.akka.persistence.sender.guaranteed.delivery;

public class CountCommand {

  private int deliveryId;
  private int updateAmount;

  public CountCommand(int deliveryId,
                      int updateAmount) {
    this.deliveryId = deliveryId;
    this.updateAmount = updateAmount;
  }

  public void setDeliveryId(int deliveryId) {
    this.deliveryId = deliveryId;
  }

  public int getDeliveryId() {
    return deliveryId;
  }

  public void setUpdateAmount(int updateAmount) {
    this.updateAmount = updateAmount;
  }

  public int getUpdateAmount() {
    return updateAmount;
  }
}