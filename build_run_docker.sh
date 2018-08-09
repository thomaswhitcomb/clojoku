#!/bin/sh
docker build -t clojoku .
docker run -p 3000:3000 -it --rm --name clojoku-running clojoku
