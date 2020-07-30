## Setup

**ONLY WORKS ON UNIX SYSTEMS**

```
$ cd python/flask
$ python3 -m venv ./venv
$ source venv/bin/activate
$ pip install bjoern gunicorn uwsgi flask
```

> **Note:**  
> To be able to install **uwsgi** and **bjoern** you must have installed the python3-dev version and the build tools. In case you have not installed them, run the following command on Ubuntu:  
> ```$ sudo apt-get install build-essential python3-dev```  
> Additionally, libev-dev is needed for bjoern.  
> ``` $ sudo apt-get install libev-dev```

### Run with bjoern as WSGI server
```
$ python3 app.py
```

### Run with gunicorn as WSGI server
```
$ gunicorn --bind 127.0.0.1:8080 app:app
```

### Run with uWSGI as WSGI server
```
$ uwsgi --socket 127.0.0.1:8080 --protocol=http -w app:app --disable-logging
```

### Exit venv
```
$ deactivate
```
