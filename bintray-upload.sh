#! /bin/sh

./gradlew build bintrayUpload -PbintrayUser=ianlet -PbintrayApiKey=$BINTRAY_API_KEY
