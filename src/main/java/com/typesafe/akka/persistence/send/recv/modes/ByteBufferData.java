package com.typesafe.akka.persistence.send.recv.modes;

public class ByteBufferData extends PacketData {
  private static final Long serialVersionUID = 1L;
  private byte[] bytes;

  public ByteBufferData(byte[] bytes) {
    this.bytes = bytes;
  }

  public void setBytes(byte[] bytes) {
    this.bytes = bytes;
  }

  public byte[] getBytes() {
    return bytes;
  }
}