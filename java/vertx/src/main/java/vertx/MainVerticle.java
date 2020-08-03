package vertx;

import io.vertx.core.AbstractVerticle;
import vertx.controller.Controller;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start() {
    vertx.createHttpServer().requestHandler(new Controller(vertx)).listen(8080);
  }
}
