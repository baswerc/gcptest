#!/bin/bash

set -e
docker build . -t gcr.io/macs-engineering/gcptest:latest
docker push gcr.io/macs-engineering/gcptest:latest

