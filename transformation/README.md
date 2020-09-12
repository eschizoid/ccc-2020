## Transformation
The purpose of this project is to read a raw json tweet from Google PubSub and sink into BigQuery for later analysis.

### Pre-requisites:
* Java 1.8.x
* Scala Scala 2.13

### Required Env variables
```
GOOGLE_APPLICATION_CREDENTIALS
```

### Running Beam Pipeline
```
$ ./bin/deploy_transformation.sh
```