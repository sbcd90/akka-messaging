package com.typesafe.akka.persistence.sender.guaranteed.delivery;

public class CountEvent extends Event {
  private int updateAmount;

  public CountEvent(int updateAmount) {
    super();
    this.updateAmount = updateAmount;
  }

  public void setUpdateAmount(int updateAmount) {
    this.updateAmount = updateAmount;
  }

  public int getUpdateAmount() {
    return updateAmount;
  }

  @Override
  public String getDescription() {
    return "Count Event created";
  }
}