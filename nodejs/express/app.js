"use strict"

const express = require('express')
const fib = require("../fibonacci")
const app = express()
app.use(express.json())

/*
    getSerialized
*/
app.get("/serialize", (request, reply) => {
    reply.send({ message: "Hello, World!" })
})


/*
    getSerializedBig
*/
app.get("/serialize/big", (request, reply) => {
    const resp = {
        family: "Elephantidae",
        scientificClassification: {
            kingdom: "Animalia",
            phylum: "Chordata",
            class: "Mammalia",
            order: "Proboscidea",
            superfamily: "Elephantoidea",
            classifier: {
                name: "John Edward Gray",
                born: {
                    year: 1800,
                    month: "February",
                    day: 12,
                    city: "Walsall",
                    country: "England"
                },
                died: {
                    year: 1875,
                    month: "March",
                    day: 7,
                    city: "London",
                    country: "England"
                },
                publications: []
            }
        }
    }

    for (let i = 0; i < 50; i++) {
        resp.scientificClassification.classifier.publications.push({
            year: 1821 + i,
            related: true,
            description: "Some discovery in " + (1821 + i)
        })
    }

    reply.send(resp)
})


/*
    getPlainText
*/
app.get("/plain/text", (request, reply) => {
    reply.type("text/plain").send("Hello, World!")
})


/*
    getQueryResult
*/
app.get("/query/:userId/tools/:offset", (request, reply) => {
    if (parseInt(request.params.userId) !== 300 ||
        parseInt(request.params.offset) !== 10 ||
        request.headers["x-api-key"] !== "zb478fb3" ||
        request.headers["x-session-id"] !== "jhg723bf" ||
        request.query.model !== "Dozer" ||
        request.query.factor !== "ATX" ||
        parseInt(request.query.length) !== 800 ||
        parseInt(request.query.width) !== 800 ||
        request.query.allow !== "true") {

        reply.status(404).send({
            code: 404,
            message: "Check your query arguments again."
        })
    } else {
        reply.send({ id: 6000, foundAt: (new Date).toISOString() })
    }
})


/*
    insertObject
*/
app.post("/insert", (request, reply) => {
    request.body.createdAt = (new Date).toISOString()
    request.body.id = 300
    reply.status(201).send(request.body)
})


/*
    getCalculated
*/
app.get("/calculate", (request, reply) => {
    reply.send({ fibonacci: fib(27) })
})


app.listen(8080)