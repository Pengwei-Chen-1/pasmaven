FROM centos
MAINTAINER pengwei_chen "pengwei_chen@qq.com"
# install jdk
RUN yum -y update && yum -y install wget && yum -y install java-1.7.0-openjdk*
# install tomcat
RUN wget http://mirrors.advancedhosters.com/apache/tomcat/tomcat-7/v7.0.73/bin/apache-tomcat-7.0.73.tar.gz
RUN tar xvf apache-tomcat-7.0.73.tar.gz
RUN mv apache-tomcat-7.0.73 /opt/tomcat
RUN rm -rf apache-tomcat-7.0.73.tar.gz
# install maven
RUN wget http://mirrors.hust.edu.cn/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
RUN tar xvf apache-maven-3.3.9-bin.tar.gz
RUN mv apache-maven-3.3.9 /opt/maven
RUN rm -rf apache-maven-3.3.9-bin.tar.gz
ADD settings.xml /opt/maven/conf/
ADD pas.xml /opt/tomcat/conf/Catalina/localhost/
ENV M2_PATH /opt/maven
ENV JAVA_HOME /usr/lib/jvm/java-1.7.0-openjdk
ENV PATH $PATH:$M2_PATH/bin:$JAVA_HOME/bin
RUN source /etc/profile
# open 8080 port
EXPOSE 8080
CMD ["/bin/bash"]
