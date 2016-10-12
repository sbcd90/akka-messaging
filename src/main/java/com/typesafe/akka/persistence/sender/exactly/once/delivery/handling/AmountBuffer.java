package com.typesafe.akka.persistence.sender.exactly.once.delivery.handling;

import java.util.ArrayList;
import java.util.List;

public class AmountBuffer {
  private final List<Integer> amounts;

  public AmountBuffer() {
    amounts = new ArrayList<>();
  }

  public void addAmount(int amount) {
    amounts.add(amount);
  }

  public List<Integer> getDeliveryIds() {
    return amounts;
  }
}