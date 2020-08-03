package vertx.controller;

import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.api.validation.HTTPRequestValidationHandler;
import io.vertx.ext.web.api.validation.ParameterType;
import io.vertx.ext.web.api.validation.ValidationException;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.impl.RouterImpl;
import vertx.model.*;

public class Controller extends RouterImpl {

  public Controller(Vertx vertx) {
    super(vertx);

    this.route().handler(BodyHandler.create());
    this.get("/serialize").handler(this::getSerialized);
    this.get("/serialize/big").handler(this::getSerializedBig);
    this.get("/plain/text").handler(this::getPlainText);
    this.get("/query/:userId/tools/:offset")
      .handler(this.getQueryResultRequestValidator())
      .handler(this::getQueryResult)
      .failureHandler(this::validationHandler)
      .failureHandler(this::notFoundHandler);
    this.post("/insert")
      .handler(this.getInsertObjectRequestValidator())
      .handler(this::insertObject)
      .failureHandler(this::validationHandler);
    this.get("/calculate").blockingHandler(this::getCalculated);
  }

  private void getSerialized(RoutingContext routingContext) {
    routingContext.response()
      .putHeader("content-type", "application/json")
      .end(new JsonObject().put("message", "Hello, World!").encode());
  }

  private void getSerializedBig(RoutingContext routingContext) {
    routingContext.response()
      .putHeader("content-type", "application/json")
      .end(JsonObject.mapFrom(new GetSerializedBig(50)).encode());
  }

  private void getPlainText(RoutingContext routingContext) {
    routingContext.response()
      .putHeader("content-type", "text/plain;charset=UTF-8")
      .end("Hello, World!");
  }

  private Handler<RoutingContext> getQueryResultRequestValidator() {
    return HTTPRequestValidationHandler.create()
      .addPathParam("userId", ParameterType.INT)
      .addPathParam("offset", ParameterType.INT)
      .addHeaderParam("x-api-key", ParameterType.GENERIC_STRING, true)
      .addHeaderParam("x-session-id", ParameterType.GENERIC_STRING, true)
      .addQueryParam("model", ParameterType.GENERIC_STRING, true)
      .addQueryParam("factor", ParameterType.GENERIC_STRING, true)
      .addQueryParam("length", ParameterType.INT, true)
      .addQueryParam("width", ParameterType.INT, true)
      .addQueryParam("allow", ParameterType.BOOL, true);
  }

  private void getQueryResult(RoutingContext routingContext) {
    if (Integer.parseInt(routingContext.pathParam("userId")) != 300 ||
      Integer.parseInt(routingContext.pathParam("offset")) != 10 ||
      !routingContext.request().getHeader("x-api-key").equals("zb478fb3") ||
      !routingContext.request().getHeader("x-session-id").equals("jhg723bf") ||
      !routingContext.queryParam("model").get(0).equals("Dozer") ||
      !routingContext.queryParam("factor").get(0).equals("ATX") ||
      Integer.parseInt(routingContext.queryParam("length").get(0)) != 800 ||
      Integer.parseInt(routingContext.queryParam("width").get(0)) != 800 ||
      !Boolean.parseBoolean(routingContext.queryParam("allow").get(0))) {

      routingContext.fail(404);
    } else {
      routingContext.response()
        .putHeader("content-type", "application/json")
        .end(JsonObject.mapFrom(new GetQueryResult(6000)).encode());
    }
  }

  private Handler<RoutingContext> getInsertObjectRequestValidator() {
    return HTTPRequestValidationHandler.create().addJsonBodySchema("{\n" +
      "  \"name\": \"string\",\n" +
      "  \"addresses\": [\n" +
      "    {\n" +
      "      \"street\": \"string\",\n" +
      "      \"number\": 0,\n" +
      "      \"city\": \"string\"\n" +
      "    }\n" +
      "  ],\n" +
      "  \"oldTown\": true\n" +
      "}");
  }

  private void insertObject(RoutingContext routingContext) {
    InsertObject insertObject;
    try {
      insertObject = routingContext.getBodyAsJson().mapTo(InsertObject.class);
    } catch (Exception e) {
      throw new ValidationException("Check your submitted body again.");
    }
    routingContext.response()
      .putHeader("content-type", "application/json")
      .end(JsonObject.mapFrom(insertObject).encode());
  }

  private void getCalculated(RoutingContext routingContext) {
    routingContext.response()
      .putHeader("content-type", "application/json")
      .end(new JsonObject().put("fibonacci", Fibonacci.getFibonacciNumber(27)).encode());
  }

  private void validationHandler(RoutingContext routingContext) {
    Throwable failure = routingContext.failure();
    if (failure instanceof ValidationException) {
      routingContext.response()
        .setStatusCode(400)
        .putHeader("content-type", "application/json")
        .end(new JsonObject().put("statusCode", 400).put("message", failure.getMessage()).encode());
    }
    routingContext.next();
  }

  private void notFoundHandler(RoutingContext routingContext) {
    if (routingContext.statusCode() == 404) {
      routingContext.response()
        .setStatusCode(404)
        .putHeader("content-type", "application/json")
        .end(new JsonObject().put("statusCode", 404).put("message", "Check your query arguments again.").encode());
    }
    routingContext.next();
  }
}
