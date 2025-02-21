#!/bin/bash

set -xe

./gradlew kotlinUpgradeYarnLock

bash ./scripts/check.sh

# simple implementation of delivering locally & then publicly
./gradlew publishToMavenLocal && ./gradlew publishAllPublicationsToSonatypeRepository closeAndReleaseStagingRepository
