package com.shippable;

import junit.framework.*;
import com.shippable.Storage;

public class TestApp extends TestCase {
  
  protected void setUp() {
    // put common setup code in here
  }
   
  protected void tearDown() {
    // put common cleanup code in here
  }

  public void testInsertAndGetData() throws Exception {
    Integer result = null;
    try {
      Storage storage = new Storage();
      storage.populate();
      result = storage.getScore();
      System.out.println(result);
    } catch (Exception e) {
      System.out.println("Error testing database");
      System.out.println(e.getMessage());
    } finally {
      assertEquals(new Integer(1234), result);
    }
  } 
}

