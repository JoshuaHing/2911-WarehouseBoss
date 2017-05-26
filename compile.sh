#!/bin/sh

mkdir -p bin/
find src -type f -name "*.java" -print | xargs javac -cp bin -d bin
cp -R MenuImage bin
cp -R images bin