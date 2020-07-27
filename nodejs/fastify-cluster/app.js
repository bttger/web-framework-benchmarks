"use strict"

const fastify = require("fastify")()

module.exports = () => {
    /*
        getSerialized
    */
    const getSerializedOptions = {
        schema: {
            response: {
                200: {
                    type: "object",
                    properties: {
                        message: { type: "string" }
                    }
                }
            }
        }
    }

    fastify.get("/serialize", getSerializedOptions, (request, reply) => {
        reply.send({ message: "Hello, World!" })
    })


    /*
        getSerializedBig
    */
    const getSerializedBigOptions = {
        schema: {
            response: {
                200: {
                    type: "object",
                    properties: {
                        family: {
                            type: "string"
                        },
                        scientificClassification: {
                            type: "object",
                            properties: {
                                kingdom: { type: "string" },
                                phylum: { type: "string" },
                                class: { type: "string" },
                                order: { type: "string" },
                                superfamily: { type: "string" },
                                classifier: {
                                    type: "object",
                                    properties: {
                                        name: { type: "string" },
                                        born: {
                                            type: "object",
                                            properties: {
                                                year: { type: "number" },
                                                month: { type: "string" },
                                                day: { type: "number" },
                                                city: { type: "string" },
                                                country: { type: "string" }
                                            }
                                        },
                                        died: {
                                            type: "object",
                                            properties: {
                                                year: { type: "number" },
                                                month: { type: "string" },
                                                day: { type: "number" },
                                                city: { type: "string" },
                                                country: { type: "string" }
                                            }
                                        },
                                        publications: {
                                            type: "array",
                                            items: {
                                                type: "object",
                                                properties: {
                                                    year: { type: "number" },
                                                    related: { type: "boolean" },
                                                    description: { type: "string" }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fastify.get("/serialize/big", getSerializedBigOptions, (request, reply) => {
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
    fastify.get("/plain/text", (request, reply) => {
        reply
            .header("content-type", "text/plain; charset=utf-8")
            .serializer((payload) => payload)
            .send("Hello, World!")
    })


    /*
        getQueryResult
    */
    const getQueryResultOptions = {
        schema: {
            query: {
                model: { type: "string" },
                factor: { type: "string" },
                length: { type: "integer" },
                width: { type: "integer" },
                allow: { type: "boolean" }
            },
            headers: {
                type: "object",
                properties: {
                    "x-api-key": { type: "string" },
                    "x-session-id": { type: "string" }
                },
                required: ["x-api-key", "x-session-id"]
            },
            params: {
                userId: { type: "integer" },
                offset: { type: "integer" }
            },
            response: {
                200: {
                    type: "object",
                    properties: {
                        id: { type: "number" },
                        foundAt: { type: "string" }
                    }
                }
            }
        }
    }

    fastify.get("/query/:userId/tools/:offset", getQueryResultOptions, (request, reply) => {
        if (request.params.userId !== 300 ||
            request.params.offset !== 10 ||
            request.headers["x-api-key"] !== "zb478fb3" ||
            request.headers["x-session-id"] !== "jhg723bf" ||
            request.query.model !== "Dozer" ||
            request.query.factor !== "ATX" ||
            request.query.length !== 800 ||
            request.query.width !== 800 ||
            request.query.allow !== true) {

            const error = new Error()
            error.statusCode = 404
            error.message = "Check your query arguments again."
            reply.send(error)
        }

        reply.send({ id: 6000, foundAt: (new Date).toISOString() })
    })


    /*
        insertObject
    */
    const insertObjectOptions = {
        schema: {
            body: {
                type: "object",
                properties: {
                    name: { type: "string" },
                    addresses: {
                        type: "array",
                        items: {
                            type: "object",
                            properties: {
                                street: { type: "string" },
                                number: { type: "integer" },
                                city: { type: "string" }
                            }
                        }
                    },
                    oldTown: { type: "boolean" }
                }
            },
            response: {
                200: {
                    type: "object",
                    properties: {
                        id: { type: "number" },
                        name: { type: "string" },
                        addresses: {
                            type: "array",
                            items: {
                                type: "object",
                                properties: {
                                    street: { type: "string" },
                                    number: { type: "integer" },
                                    city: { type: "string" }
                                }
                            }
                        },
                        oldTown: { type: "boolean" },
                        createdAt: { type: "string" }
                    }
                }
            }
        }
    }

    fastify.post("/insert", insertObjectOptions, (request, reply) => {
        request.body.createdAt = (new Date).toISOString()
        request.body.id = 300
        reply.code(201).send(request.body)
    })


    // Start the server
    fastify.listen(8080, (err) => {
        if (err) {
            fastify.log.error(err)
            process.exit(1)
        }
    })
}
