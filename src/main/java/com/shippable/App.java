package com.shippable;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import spark.servlet.SparkApplication;

public class App implements SparkApplication {
  @Override
  public void init() {
    Spark.get(new Route("/") {
      @Override
      public Object handle(Request request, Response response) {
        return "Hello world!";
      }
    });
  }
}
