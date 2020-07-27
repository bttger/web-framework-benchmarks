"use strict"

const cluster = require("cluster");
const os = require("os");
const app = require("./app");

if (cluster.isMaster) {
    const cpuCount = os.cpus().length;

    for (let i = 0; i < cpuCount; i++) {
        cluster.fork();
    }
} else {
    console.log("Start cluster process...")
    app();
}

cluster.on("exit", function (worker) {
    console.log(`Worker ${worker.id} died`);
    console.log("Starting a new cluster process...");
    cluster.fork();
});