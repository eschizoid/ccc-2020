#!/usr/bin/env bash

cd streaming || exit

docker-compose up \
  --build
