#!/bin/bash
#Set Script Name variable
SCRIPT=`basename ${BASH_SOURCE[0]}`

#Set fonts for Help.
NORM=`tput sgr0`
BOLD=`tput bold`
REV=`tput smso`

#Help function
function HELP {
  echo -e \\n"Usage for ${BOLD}${SCRIPT}.${NORM}"\\n
  echo "${REV}-v${NORM}  Docker image tag"
  echo -e "${REV}-h${NORM}  Displays this help message. No further functions are performed."\\n
  echo -e "Example: ${BOLD}$SCRIPT -v develop ${NORM}"\\n
  exit 1
}

#Check the number of arguments. If none are passed, print help and exit.
NUMARGS=$#
if [ $NUMARGS -eq 0 ]; then
  HELP
fi

#CLI options
while getopts :v:h FLAG; do
  case $FLAG in
    v)  #set option "v"
      export TAG=$OPTARG
      ;;
    h)  #show help
      HELP
      ;;
    \?) #unrecognized option - show help
      echo -e \\n"Option -${BOLD}$OPTARG${NORM} not allowed."
      HELP
      ;;
  esac
done

shift $((OPTIND-1))  #This tells getopts to move on to the next argument.

echo "Notifications Server Docker Tag ::" $TAG

./gradlew clean build

rm ./docker/notifications_server-1.0.0.jar
cp ./build/libs/notifications_server-*.jar ./docker/notifications_server-1.0.0.jar

docker rmi notifications-server:*

docker build --tag="notifications-server:$TAG" .

#// docker push $REPOSITORY_URL/pots/notifications-server:$TAG