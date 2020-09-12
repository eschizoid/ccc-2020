#!/usr/bin/env bash

cd streaming || exit

envsubst < ./app.tml.yaml > app.yaml

gcloud app deploy app.yaml
