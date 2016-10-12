package com.typesafe.akka.persistence.sender.guaranteed.delivery;

public class ExternalRequest {

  private int updateAmount;

  public ExternalRequest(int updateAmount) {
    this.updateAmount = updateAmount;
  }

  public void setUpdateAmount(int updateAmount) {
    this.updateAmount = updateAmount;
  }

  public int getUpdateAmount() {
    return updateAmount;
  }
}