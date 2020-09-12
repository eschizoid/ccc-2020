#!/usr/bin/env bash

gcloud app services delete ccc-2020-streaming

gcloud dataflow jobs cancel \
  --project=ccc-2020-289323 \
  --region=us-east1 \
  2020-09-13_15_10_50-17959141374415049171
