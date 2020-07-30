import bjoern
from flask import Flask
from flask import request
import datetime
from fibonacci import fib
app = Flask(__name__)


@app.route("/serialize")
def getSerialized():
    return {"message": "Hello, World!"}


@app.route("/serialize/big")
def getSerializedBig():
    resp = {
        "family": "Elephantidae",
        "scientificClassification": {
            "kingdom": "Animalia",
            "phylum": "Chordata",
            "class": "Mammalia",
            "order": "Proboscidea",
            "superfamily": "Elephantoidea",
            "classifier": {
                "name": "John Edward Gray",
                "born": {
                    "year": 1800,
                    "month": "February",
                    "day": 12,
                    "city": "Walsall",
                    "country": "England"
                },
                "died": {
                    "year": 1875,
                    "month": "March",
                    "day": 7,
                    "city": "London",
                    "country": "England"
                },
                "publications": []
            }
        }
    }
    for i in range(0, 50):
        resp["scientificClassification"]["classifier"]["publications"].append({
            "year": 1821 + i,
            "related": True,
            "description": "Some discovery in " + str(1821 + i)
        })
    return resp


@app.route("/plain/text")
def getPlainText():
    return "Hello, World!"


@app.route("/query/<userId>/tools/<offset>")
def getQueryResult(userId, offset):
    if int(userId) == 300 and \
            int(offset) == 10 and \
            request.headers.get("x-api-key", "") == "zb478fb3" and \
            request.headers.get("x-session-id", "") == "jhg723bf" and \
            request.args.get("model", "") == "Dozer" and \
            request.args.get("factor", "") == "ATX" and \
            request.args.get("length", 0, int) == 800 and \
            request.args.get("width", 0, int) == 800 and \
            request.args.get("allow", False, bool):
        return {"id": 6000, "foundAt": datetime.datetime.now().isoformat()}

    return "Error: Check your query arguments again.", 404


@app.route("/insert", methods=["POST"])
def insertObject():
    body = request.get_json()
    body["createdAt"] = datetime.datetime.now().isoformat()
    body["id"] = 300
    return body, 201


@app.route("/calculate")
def getCalculated():
    return {"fibonacci": fib(27)}


if __name__ == "__main__":
    # app.run(port=8080) # only used during development (integrated werkzeug wsgi server)
    bjoern.run(app, "127.0.0.1", 8080)
