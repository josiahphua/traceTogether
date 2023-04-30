#!/usr/bin/env sh

echo "SPRING_PROFILES_ACTIVE ===== $SPRING_PROFILES_ACTIVE"
echo "IMAGE_TAG ===== $IMAGE_TAG"``

/bin/sh -c "java -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom -jar app.jar"
