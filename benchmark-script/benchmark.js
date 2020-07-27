"use strict"

const fs = require("fs");
const autocannon = require("autocannon")

const title = process.argv[2]
const connections = 10
const amount = 1000000
const warmupDuration = 30

async function warmup() {
    const result = await autocannon({
        title: title + " - WARM UP",
        url: "http://localhost:8080/serialize",
        connections: connections,
        duration: warmupDuration
    })
    return result
}

async function getSerialized() {
    const result = await autocannon({
        title: title + " - getSerialized",
        url: "http://localhost:8080/serialize",
        connections: connections,
        amount: amount
    })
    return result
}

async function getSerializedBig() {
    const result = await autocannon({
        title: title + " - getSerializedBig",
        url: "http://localhost:8080/serialize/big",
        connections: connections,
        amount: amount
    })
    return result
}

async function getPlainText() {
    const result = await autocannon({
        title: title + " - getPlainText",
        url: "http://localhost:8080/plain/text",
        connections: connections,
        amount: amount
    })
    return result
}

async function getQueryResult() {
    const result = await autocannon({
        title: title + " - getQueryResult",
        url: "http://127.0.0.1:8080/query/300/tools/10?model=Dozer&factor=ATX&length=800&width=800&allow=true",
        connections: connections,
        amount: amount,
        headers: {
            "x-api-key": "zb478fb3",
            "x-session-id": "jhg723bf"
        }
    })
    return result
}

async function insertObject() {
    const result = await autocannon({
        title: title + " - insertObject",
        url: "http://127.0.0.1:8080/insert",
        connections: connections,
        amount: amount,
        body: '{"name":"Sightseeing","addresses":[{"street":"Breite Straße","number":89,"city":"Lübeck"},{"street":"Breite Straße","number":89,"city":"Lübeck"}],"oldTown":true}'
    })
    return result
}

async function main() {
    let results = []

    await warmup()
    results.push(await getSerialized())
    results.push(await getSerializedBig())
    results.push(await getPlainText())
    results.push(await getQueryResult())
    results.push(await insertObject())

    fs.writeFileSync(`../results/${title}.json`, JSON.stringify(results, null, 4));
}

main()