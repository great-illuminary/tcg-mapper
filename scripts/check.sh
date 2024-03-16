#!/bin/bash

set -xe

# running validation
./gradlew ktlint detekt
./gradlew check
