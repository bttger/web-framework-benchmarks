## Benchmark Script

To run the benchmark script you must first start the web server and then navigate to the `benchmark-script` folder. Run the following command:

```
node benchmark.js <name-of-the-web-framework>
```

You can also change the constants in the `benchmark.js` file for testing purposes (e.g. reducing the amount of requests or commenting out the `await warmup()`).

After all benchmark tests have been run, it will automatically create a new file `<name-of-the-web-framework>.json` in the `results` folder.