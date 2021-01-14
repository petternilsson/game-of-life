#!/bin/sh
rows=20
columns=20
probability=0.3
iterations=20
while getopts r:c:p:i: flag
do
    case "${flag}" in
        r) rows=${OPTARG};;
        c) columns=${OPTARG};;
        p) probability=${OPTARG};;
        i) iterations=${OPTARG};;
    esac
done

java -jar app/build/libs/app.jar $rows $columns $probability $iterations
