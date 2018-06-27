#!/bin/sh

tomcatValue=`ps -ef|grep jdk|grep -v grep|awk '{print $2}'`

OPT=$1

if [  -z "$OPT" ]; then

if [ ${#tomcatValue} -ne 0 ];  then
          kill -9  `ps -ef|grep jdk|grep -v grep|awk '{print $2}'`
fi

echo '=========第一步开始打包==========='
mvn clean package
devSrc=target/*.war
#打包路径
goalSrc=/data/tomcat/webapps/ROOT.war
rm -rf /data/tomcat/webapps
mkdir  /data/tomcat/webapps
#删除tomcat记录
rm -rf /data/tomcat/work
echo '=========第二步copy到容器==========='
cp -R $devSrc $goalSrc
echo '.'
echo '..'
echo '....'
echo '......'
echo '........'
echo '=========启动TOMCAT==========='
 #运行路径
sh /data/tomcat/bin/startup.sh
echo "开启 tomcat 成功"
tail -f /data/tomcat/logs/catalina.out
else
   if [ $OPT == stop ]; then
           if [ ${#tomcatValue} -ne 0 ];  then
                     kill -9  `ps -ef|grep jdk|grep -v grep|awk '{print $2}'`
                     echo "已关闭"
           else
                echo "未启动"
           fi
   fi
   if [ $OPT == start ]; then
              if [ ${#tomcatValue} -ne 0 ];  then
                        kill -9  `ps -ef|grep jdk|grep -v grep|awk '{print $2}'`
                        echo "已关闭"
                        sh /data/tomcat/bin/startup.sh
                        echo "开启 tomcat 成功"
                        tail -f /data/tomcat/logs/catalina.out
              else
                   sh /data/tomcat/bin/startup.sh
                   echo "开启 tomcat 成功"
                   tail -f /data/tomcat/logs/catalina.out
              fi
   fi
fi




