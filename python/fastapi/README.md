## Setup

```
$ cd python/fastapi
$ python3 -m venv ./venv
$ source venv/bin/activate
$ pip install fastapi[all]
```

### Run with uvicorn as WSGI server
```
$ uvicorn app:app --port 8080 --log-level error
```

### Exit venv
```
$ deactivate
```
