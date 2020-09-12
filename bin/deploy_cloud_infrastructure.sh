#!/usr/bin/env bash

echo "Creating Storage resources"
gsutil mb \
  -c standard \
  -l us-east1 \
  gs://chicago-cloud-conference-2020

gsutil acl ch -u \
  AllUsers:R "gs://chicago-cloud-conference-2020"

echo "Creating PubSub resources"
gcloud pubsub topics create tweets \
  --message-storage-policy-allowed-regions=us-east1

echo "Creating BigQuery resources"
gcloud alpha bq tables create tweets \
  --dataset=chicago_cloud_conference \
  --schema-file="./bin/tweets_schema.json"
