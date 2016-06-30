#! /bin/bash

# Kevin Boussard 2016
#
# Script to install WAR with Tomcat

USER="root"
PASSWORD="password"
TOMCAT_WEBAPPS_PATH="/var/lib/tomcat8/webapps"
WAR_FILE="component-imie-0.1.0.war"

echo "#####################APT-GET UPDATE######################"
apt-get update 

# Install Tomcat Server
echo "#####################Tomcat Server Install######################"
debconf-set-selections <<< "mysql-server mysql-server/root_password password $PASSWORD"
debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $PASSWORD"

aptitude -y install tomcat8 php php-cli php-xml mysql-server mysql-client

if [ $? -ne 0 ]; then
     exit -1
fi

echo "DONE"

echo "########################Deploy WAR FILE#########################"
cp $WAR_FILE $TOMCAT_WEBAPPS_PATH

  if [ $? -ne 0 ]; then
     exit -1
  fi

echo "DONE"

echo "########################Restart Tomcat8#########################"
service tomcat8 restart

  if [ $? -ne 0 ]; then
     exit -1
  fi

echo "DONE"

echo "########################Create Database#########################"
mysql -u $USER -p$PASSWORD -e "create database component";

  if [ $? -ne 0 ]; then
     exit -1
  fi
echo "DONE"


