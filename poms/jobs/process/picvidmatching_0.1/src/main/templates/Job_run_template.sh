#!/bin/sh
cd `dirname $0`
ROOT_PATH=`pwd`
java -Dtalend.component.manager.m2.repository=$ROOT_PATH/../lib -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .:$ROOT_PATH:$ROOT_PATH/../lib/routines.jar:$ROOT_PATH/../lib/com.mongodb_2.6.5.1.jar:$ROOT_PATH/../lib/dom4j-1.6.1.jar:$ROOT_PATH/../lib/ffmpeg-2.1.1-windows-x86.jar:$ROOT_PATH/../lib/ffmpeg-2.1.1-windows-x86_64.jar:$ROOT_PATH/../lib/javacpp.jar:$ROOT_PATH/../lib/javacv-windows-x86.jar:$ROOT_PATH/../lib/javacv-windows-x86_64.jar:$ROOT_PATH/../lib/javacv.jar:$ROOT_PATH/../lib/log4j-1.2.17.jar:$ROOT_PATH/../lib/mongo-java-driver-3.5.0.jar:$ROOT_PATH/../lib/opencv-2.4.8-windows-x86.jar:$ROOT_PATH/../lib/opencv-2.4.8-windows-x86_64.jar:$ROOT_PATH/../lib/xuggle-xuggler-noarch-5.4.jar:$ROOT_PATH/picvidmatching_0_1.jar: sqlnosqlmigration.picvidmatching_0_1.picVidMatching  "$@"