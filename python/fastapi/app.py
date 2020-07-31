from typing import List
from fastapi import FastAPI, Header
from fastapi.responses import PlainTextResponse, JSONResponse
import json
from pydantic import BaseModel
from typing import List
import datetime
from fibonacci import fib

app = FastAPI()


@app.get("/serialize")
def getSerialized():
    return {"message": "Hello, World!"}


@app.get("/serialize/big")
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


@app.get("/plain/text")
def getPlainText():
    return PlainTextResponse("Hello, World!")


@app.get("/query/{userId}/tools/{offset}")
def getQueryResult(userId: int, offset: int, model: str, factor: str, length: int, width: int, allow: bool, x_api_key: str = Header(str), x_session_id: str = Header(str)):
    if userId == 300 and \
            offset == 10 and \
            x_api_key == "zb478fb3" and \
            x_session_id == "jhg723bf" and \
            model == "Dozer" and \
            factor == "ATX" and \
            length == 800 and \
            width == 800 and \
            allow:
        return {"id": 6000, "foundAt": datetime.datetime.now().isoformat()}

    return JSONResponse({"message": "Error: Check your query arguments again."}, 404)


class Address(BaseModel):
    street: str
    number: int
    city: str


class ObjectIn(BaseModel):
    name: str
    addresses: List[Address]
    oldTown: bool


class ObjectOut(BaseModel):
    name: str
    addresses: List[Address]
    oldTown: bool
    createdAt: str
    id: int


@app.post("/insert", response_model=ObjectOut, status_code=201)
def insertObject(body: ObjectIn):
    resp = json.loads(body.json())
    resp["createdAt"] = datetime.datetime.now().isoformat()
    resp["id"] = 300
    return resp


@app.get("/calculate")
def getCalculated():
    return {"fibonacci": fib(27)}
