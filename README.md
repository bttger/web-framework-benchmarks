# Web Framework Benchmarks

**Benchmarking the overhead ⚙️ and latency ⏱️ of different popular web frameworks.**

Head over to the [OpenAPI Definition](https://bttger.github.io/web-framework-benchmarks/) to see how the web frameworks are benchmarked. The corresponding [yaml definition file](https://github.com/bttger/web-framework-benchmarks/blob/master/OPEN_API_DEFINITION.yaml) lies in this repository. It defines the interface which every web framework has to implement. The endpoints are chosen so that they cover the performance of the **routing**, **parsing** (of request parameters) and **serialization** (of responses) as distinctly as possible.

## Distinction from Other Benchmarks

For the tests we use [autocannon](https://github.com/mcollina/autocannon). Unlike other benchmarks (e.g. the fastify benchmarks) we **do not use HTTP pipelining**, since no major browser supports it and its usage is uncommon (probably due to the fact that HTTP/2 multiplexing is much better), as well as we **do not connect to any database** (unlike the TechEmpower benchmarks) to only quantify the framework's overhead. Another big difference in the test execution is that we **do not run the test for a certain time**, but that each framework has to answer the same number of requests. Furthermore we **focus on the latency** and record the latency percentiles.

## Selection of Web Frameworks

The goal is to cover popular web frameworks with a relatively large user base. There are hundreds of web frameworks out there, and new frameworks are emerging every day. This is definitely a great thing, but when it comes to production-readiness, there are only a few projects left. **Long-term support** and a **large developer community** are important, especially in the enterprise field. This is also the reason why the selection depends largely on popularity. Accordingly, each benchmark result will include the number of stars and contributors on GitHub.

### Proposed Web Frameworks

#### Node.js
- Express ✔️
- Nest
- Koa
- Sails
- Fastify ✔️

#### Python
- Flask ✔️
- Django
- FastAPI

#### Java
- Spring
- Play
- Vert.x

#### Go
- Gin

#### Ruby
- Rails

#### PHP
- Laravel
- Symfony
- Yii2

## Contributing

This benchmark project is ideal for anyone who would like to get a taste of a new web framework and compare different frameworks in terms of both performance and code. **Everyone is encouraged to contribute** a new web framework via pull request or propose a new one via the issue tracker.

When deciding which framework to implement, you can either choose one from the [list of proposed one's](#proposed-web-frameworks), which does not yet have a check mark, or choose another one of your choice. We don't have a hard limit for popularity, but the framework should have at least about 5k stars or have gained a lot of traction in the recent past. When opening a pull request with the code for a new one, you must stick to the [defined interface](https://github.com/bttger/web-framework-benchmarks/blob/master/OPEN_API_DEFINITION.yaml). It is kept simple and **also practices working with web frameworks**. And of course, you can also improve existing code to demonstrate how to achieve better performance. 🏎️

So give it a try, help the dev community to **make better decisions** and to choose an efficient and therefore cost-saving and environmentally friendly web framework. 🤓

## Results

