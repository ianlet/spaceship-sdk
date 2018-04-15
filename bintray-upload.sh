#! /bin/sh

./gradlew build bintrayUpload -PbintrayUser=$BINTRAY_USER -PbintrayApiKey=$BINTRAY_API_KEY
