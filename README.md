# CS Games 2018 - TSE - Spaceship SDK

SDK used by the participants to help them work on their challenges and track their progress with the acceptance tests.
It is versionned to progressively release more features and acceptance tests.

## Requirements

- java 8
- mongodb

## Installation

Ask your participants to add the following dependency to their `build.gradle` (see
[ianlet/spaceship-control](https://github.com/ianlet/spaceship-control) for an example):

```
  compile 'org.csgames:spaceship-sdk:0.0.+'
```

## Usage

This repository contains multiple branches corresponding to the different releases of the SDK that can be uploaded to
Bintray with the following command:

```
BITRAY_USER=[your username] BINTRAY_API_KEY=[your api key] ./bintray-upload.sh
```

You will then have to go to your bintray account to manually publish the versions you uploaded.

If you want to write new acceptance tests, head to the directory `src/main/resources`. You will find a couple of
directories. Open the `user_stories` directory and write a new `json` file with the same structure as the others. Then
you'll have to write scenarios, events and expected results to correctly track the progress of the participants.

Everytime a participant uses the SDK, an event will be stored in the persistence. The acceptance tests will then check
whether the results described in the `json` files occurred or not.
