package com.typesafe.akka.persistence.receiver;

import java.io.Serializable;

public class Command implements Serializable {
  private static final Long serialVersionUID = 1L;
  private final String data;

  public Command(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }
}