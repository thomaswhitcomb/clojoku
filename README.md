# clojoku

## Overview

This is a Sudoku solver written in Clojure.

## How to build it

The project is managed with `lein`.  Install instructions are [here](http://leiningen.org/).

Run `lein test`

## Run it under AWS Lambda

Put your AWS API credentials in your ~/.aws/credentials.  The `lambda-deploy.sh` script depends upon the default profile in your ~/.aws/credentials file.  If you want to use a different profile, you will need to modify the script.


## Run the solver

The `run.sh` has an example of how to run a lambda request.  The lambda function has a single input which is the 9x9 sudoku grid flattened into a single of 81 digits.