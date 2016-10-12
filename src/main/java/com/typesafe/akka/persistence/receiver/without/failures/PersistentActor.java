package com.typesafe.akka.persistence.receiver.without.failures;

import akka.japi.Procedure;
import akka.persistence.SaveSnapshotSuccess;
import akka.persistence.SnapshotOffer;
import akka.persistence.UntypedPersistentActor;
import com.typesafe.akka.persistence.receiver.Command;
import com.typesafe.akka.persistence.receiver.Event;
import com.typesafe.akka.persistence.receiver.TestState;

import java.util.Arrays;

public class PersistentActor extends UntypedPersistentActor {

  private TestState testState = new TestState();

  public String persistenceId() {
    return "sample-id-1";
  }

  public int getNumEvents() {
    return testState.size();
  }

  @Override
  public void onReceiveRecover(Object msg) throws Throwable {
    if (msg instanceof Event) {
      testState.update((Event) msg);
    } else if (msg instanceof SnapshotOffer) {
      testState = (TestState) ((SnapshotOffer) msg).snapshot();
    } else {
      unhandled(msg);
    }
  }

  @Override
  public void onReceiveCommand(Object msg) throws Throwable {
    if (msg instanceof Command) {
      final String data = ((Command) msg).getData();
      final Event event1 = new Event(data + "-" + getNumEvents());
      final Event event2 = new Event(data + "-" + (getNumEvents() + 1));
      persist(Arrays.asList(event1, event2), new Procedure<Event>() {
        @Override
        public void apply(Event event) throws Exception {
          testState.update(event);
          if (event.equals(event2)) {
            getContext().system().eventStream().publish(event);
          }
        }
      });
    } else if (msg.equals("snap")) {
      saveSnapshot(testState.copy());
    } else if (msg.equals("print")) {
      System.out.println(testState);
    } else if (msg instanceof SaveSnapshotSuccess) {
      System.out.println(((SaveSnapshotSuccess) msg).metadata().toString());
    }
    else {
      unhandled(msg);
    }
  }
}