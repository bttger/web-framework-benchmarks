package bttger.webframeworkbenchmarks.spring.controller;

import bttger.webframeworkbenchmarks.spring.model.*;
import bttger.webframeworkbenchmarks.spring.model.QueryError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @GetMapping("/serialize")
    public GetSerialized getSerialized() {
        return new GetSerialized("Hello, World!");
    }

    @GetMapping("/serialize/big")
    public GetSerializedBig getSerializedBig() {
        return new GetSerializedBig(50);
    }

    @GetMapping("/plain/text")
    public String getPlainText() {
        return "Hello, World!";
    }

    @GetMapping("/query/{userId}/tools/{offset}")
    public ResponseEntity<GetQueryResult> getQueryResult(
            @PathVariable int userId,
            @PathVariable int offset,
            @RequestHeader("x-api-key") String apiKey,
            @RequestHeader("x-session-id") String sessionId,
            @RequestParam String model,
            @RequestParam String factor,
            @RequestParam int length,
            @RequestParam int width,
            @RequestParam boolean allow) throws QueryError {

        if (userId != 300 ||
                offset != 10 ||
                !apiKey.equals("zb478fb3") ||
                !sessionId.equals("jhg723bf") ||
                !model.equals("Dozer") ||
                !factor.equals("ATX") ||
                length != 800 ||
                width != 800 ||
                !allow) {

            throw new QueryError("Error: Check your arguments again.", 404);
        } else {
            return ResponseEntity.ok(new GetQueryResult(6000));
        }
    }

    @PostMapping(path = "/insert", consumes = "application/json")
    public InsertObject insertObject(@RequestBody InsertObject insertObject) {
        return insertObject;
    }

    @GetMapping("/calculate")
    public GetCalculated getCalculated() {
        return new GetCalculated(Fibonacci.getFibonacciNumber(27));
    }
}
