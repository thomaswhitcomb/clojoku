#!/bin/sh
NAME=clojoku
# run lein uberjar to create the zip-file
# Create the arn:aws:iam::079759702379:role/lambda_exec_role first

aws lambda get-function --function-name $NAME > /dev/null

if [[ $? == 0 ]];then

aws lambda update-function-code \
  --function-name $NAME \
  --zip-file fileb://./target/uberjar/clojoku-0.1.0-standalone.jar

else

aws lambda create-function \
  --function-name $NAME \
  --handler clojoku.lambda::handler \
  --runtime java8 \
  --memory 512 \
  --timeout 10 \
  --role arn:aws:iam::079759702379:role/lambda_exec_role \
  --zip-file fileb://./target/uberjar/clojoku-0.1.0-standalone.jar

fi
