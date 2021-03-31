#!/bin/bash

export ENV=$1

rm notifications_server-0.1.jar
cp ./build/libs/notifications_server-*.jar notifications_server-0.1.jar

if [[ $ENV == *aws* ]]; then
    scp -i ../microDev.pem notifications_server-1.0.jar ec2-user@ec2-3-249-160-105.eu-west-1.compute.amazonaws.com:~/
    scp -i ../microDev.pem api.sh ec2-user@ec2-3-249-160-105.eu-west-1.compute.amazonaws.com:~/
fi