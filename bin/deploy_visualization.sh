#!/usr/bin/env bash

cd visualization || exit

gcloud beta dataproc clusters create chicago-cloud-conference-2020 \
  --optional-components=ANACONDA,JUPYTER \
  --image-version=1.4 \
  --enable-component-gateway \
  --bucket=chicago-cloud-conference-2020 \
  --region=us-east1 \
  --project=ccc-2020-289323 \
  --metadata 'PIP_PACKAGES=google-cloud-bigquery google-cloud-storage numpy pandas matplotlib'
