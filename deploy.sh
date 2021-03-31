#!/bin/bash

export ENV=$1

rm pots-server-0.1.jar
cp ./build/libs/pots.server-*.jar pots-server-0.1.jar

if [[ $ENV == *aws* ]]; then
    scp -i ../microDev.pem pots-server-1.0.jar ec2-user@ec2-3-249-160-105.eu-west-1.compute.amazonaws.com:~/
    scp -i ../microDev.pem api.sh ec2-user@ec2-3-249-160-105.eu-west-1.compute.amazonaws.com:~/
fi