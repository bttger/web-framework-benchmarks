"use strict"

const fs = require("fs");
const autocannon = require("autocannon")

const TITLE = process.argv[2]
const CONNECTIONS = 10
const AMOUNT = 1000000
const WARMUP_DURATION = 30
const PORT = 8080

async function warmup() {
    const result = await autocannon({
        title: TITLE + " - WARM UP",
        url: `http://localhost:${PORT}/serialize`,
        connections: CONNECTIONS,
        duration: WARMUP_DURATION
    })
    return result
}

async function getSerialized() {
    const result = await autocannon({
        title: TITLE + " - getSerialized",
        url: `http://localhost:${PORT}/serialize`,
        connections: CONNECTIONS,
        amount: AMOUNT
    })
    return result
}

async function getSerializedBig() {
    const result = await autocannon({
        title: TITLE + " - getSerializedBig",
        url: `http://localhost:${PORT}/serialize/big`,
        connections: CONNECTIONS,
        amount: AMOUNT
    })
    return result
}

async function getPlainText() {
    const result = await autocannon({
        title: TITLE + " - getPlainText",
        url: `http://localhost:${PORT}/plain/text`,
        connections: CONNECTIONS,
        amount: AMOUNT
    })
    return result
}

async function getQueryResult() {
    const result = await autocannon({
        title: TITLE + " - getQueryResult",
        url: `http://localhost:${PORT}/query/300/tools/10?model=Dozer&factor=ATX&length=800&width=800&allow=true`,
        connections: CONNECTIONS,
        amount: AMOUNT,
        headers: {
            "x-api-key": "zb478fb3",
            "x-session-id": "jhg723bf"
        }
    })
    return result
}

async function insertObject() {
    const result = await autocannon({
        title: TITLE + " - insertObject",
        url: `http://localhost:${PORT}/insert`,
        connections: CONNECTIONS,
        amount: AMOUNT,
        headers: { "content-type": "application/json" },
        body: '{"name":"Sightseeing","addresses":[{"street":"Breite Straße","number":89,"city":"Lübeck"},{"street":"Breite Straße","number":89,"city":"Lübeck"}],"oldTown":true}'
    })
    return result
}

async function getCalculated() {
    const result = await autocannon({
        title: TITLE + " - getCalculated",
        url: `http://localhost:${PORT}/calculate`,
        connections: CONNECTIONS,
        amount: AMOUNT / 100
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
    results.push(await getCalculated())

    fs.writeFileSync(`../results/${TITLE}.json`, JSON.stringify(results, null, 4));
}

main()