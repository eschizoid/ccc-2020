#!/usr/bin/env bash

./gradlew clean \
  build \
  shadowJar

cd transformation || exit

java -jar build/libs/transformation-1.0-all.jar \
  --project=ccc-2020-289323 \
  --runner=DataflowRunner \
  --streaming=true \
  --region=us-east1 \
  --tempLocation=gs://chicago-cloud-conference-2020/temp/ \
  --stagingLocation=gs://chicago-cloud-conference-2020/jars/ \
  --filesToStage=build/libs/transformation-1.0-all.jar \
  --maxNumWorkers=2 \
  --numWorkers=1
