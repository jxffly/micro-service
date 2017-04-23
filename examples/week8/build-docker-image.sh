#!/bin/sh

current_dir=`pwd`

cd "$current_dir/emall-api" && \
 mvn clean package -Psandboxing -DskipTests && \
 mvn install:install-file -Dfile=target/emall-api-1.0.0.jar \
  -DgroupId=io.junq.examples.emall.boot \
  -DartifactId=emall-api -Dversion=1.0.0 -Dpackaging=jar

cd $current_dir

for service in `ls $current_dir | egrep "service|server"`
do
  cd "$current_dir/$service"
  mvn clean package -Psandboxing -DskipTests && docker build -t stuq-1160-emall/$service .
done
