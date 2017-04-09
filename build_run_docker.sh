#!/bin/sh

docker build -t clojoku .

echo "=== Routes request on port 80 to 8080"
echo "=== http://localhost:80/"
echo "=== For repl run: docker run -i -t clojure /bin/bash"
docker run -p 80:8080 -it --rm --name clojoku-running clojoku
