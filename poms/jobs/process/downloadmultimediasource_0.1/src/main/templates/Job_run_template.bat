%~d0
cd %~dp0
java -Dtalend.component.manager.m2.repository=%cd%/../lib -Xms256M -Xmx1024M -Dfile.encoding=UTF-8 -cp .;../lib/routines.jar;../lib/cimt.talendcomp.lob.download-1.0.jar;../lib/dom4j-1.6.1.jar;../lib/log4j-1.2.17.jar;../lib/mysql-connector-java-8.0.12.jar;downloadmultimediasource_0_1.jar; sqlnosqlmigration.downloadmultimediasource_0_1.downloadMultimediaSource  %*