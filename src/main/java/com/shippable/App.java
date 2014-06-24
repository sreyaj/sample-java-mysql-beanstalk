package com.shippable;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.servlet.SparkApplication;

public class App implements SparkApplication {
  private Storage storage;

  public App() throws Exception {
    storage = new Storage();
  }

  @Override
  public void init() {
    Spark.get(new Route("/") {
      @Override
      public Object handle(Request request, Response response) {
        storage.populate();
        Integer score = null;
        try {
          score = storage.getScore();
        } catch (Exception e) {
          e.printStackTrace();
        }

        return "Hello world, " + score + "!";
      }
    });
  }
}
