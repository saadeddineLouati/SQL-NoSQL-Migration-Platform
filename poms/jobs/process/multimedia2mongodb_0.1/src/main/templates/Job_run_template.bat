%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository=%cd%/../lib -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .;../lib/routines.jar;../lib/com.mongodb_2.6.5.1.jar;../lib/dom4j-1.6.1.jar;../lib/log4j-1.2.17.jar;../lib/mongo-java-driver-3.5.0.jar;../lib/mongo-java-driver-3.8.2.jar;multimedia2mongodb_0_1.jar; sqlnosqlmigration.multimedia2mongodb_0_1.multimedia2mongoDB  %*