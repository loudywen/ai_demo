package com.devon.demo.man;

import org.junit.Test;

/**
 * Created by Devon on 3/11/2017.
 */
public class RandomTest {

  @Test
  public void Singleton() {
      for(int x=0 ; x<5; x++){
        Thread thread = new Thread(() -> System.out.println("==="+OnlyOneObject.getInstance().toString()));
        thread.start();
      }
  }


  private static class OnlyOneObject {

    public static OnlyOneObject getInstance() {
      return SingletonHelper.getInstance;
    }

    private static class SingletonHelper {

      private static final OnlyOneObject getInstance = new OnlyOneObject();
    }
  }
}
