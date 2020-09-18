#!/usr/bin/env bash

echo "Deleting App Engine streaming app..."
gcloud app services delete ccc-2020-streaming

echo "Deleting Dataflow transformation app..."
gcloud dataflow jobs cancel \
  --project=ccc-2020-289323 \
  --region=us-east1 \
  2020-09-20_11_05_11-12337566833293480703

echo "Deleting Dataproc visualization app..."
gcloud dataproc clusters delete chicago-cloud-conference-2020 \
  --region=us-east1

