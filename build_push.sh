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

# script to get the aws user id and token into to  gradle
LOGIN_STRING='aws ecr get-login --region eu-west-1 --no-include-email --profile default'
${LOGIN_STRING} > login.sh 

USERNAME=$(echo $(cut -d' ' -f4 login.sh))
TOKEN=$(echo $(cut -d' ' -f6 login.sh))

./copy_jar.sh

REPOSITORY_URL=$(echo $(cut -d' ' -f7 login.sh)| cut -c9-)
chmod +x login.sh
./login.sh

# Added new code to pull the base image first inorder for gradle docker plugin to build the image
docker pull $REPOSITORY_URL/essearch/ess-jre:1.1
docker pull $REPOSITORY_URL/essearch/ubuntu-ess-jre:1.0

docker build --tag="$REPOSITORY_URL/pots/notifications-server:$TAG" .

docker push $REPOSITORY_URL/pots/notifications-server:$TAG

docker rmi $REPOSITORY_URL/pots/notifications-server:$TAG
