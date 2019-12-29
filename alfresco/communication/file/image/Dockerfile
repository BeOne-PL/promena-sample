FROM alfresco/alfresco-content-repository-community:6.1.2-ga

ARG TOMCAT_DIR=/usr/local/tomcat

RUN yum install -y wget && \
    wget -O $TOMCAT_DIR/amps/alfresco-promena-core-1.0.0.amp http://nexus.office.beone.pl/repository/releases/pl/beone/promena/alfresco/module/alfresco-promena-core/1.0.0/alfresco-promena-core-1.0.0.amp && \
    wget -O $TOMCAT_DIR/amps/alfresco-promena-connector-http-1.0.0.amp http://nexus.office.beone.pl/repository/releases/pl/beone/promena/alfresco/module/connector/alfresco-promena-connector-http/1.0.0/alfresco-promena-connector-http-1.0.0.amp && \
    wget -O $TOMCAT_DIR/amps/alfresco-jvm-console-1.0.0.amp https://github.com/Skotar/alfresco-jvm-console/releases/download/1.0.0/alfresco-jvm-console-1.0.0.amp && \
    wget -O $TOMCAT_DIR/webapps/alfresco/WEB-INF/lib/mirror-jdk-application-model-1.0.0.jar http://nexus.office.beone.pl/repository/releases/pl/beone/promena/transformer/mirror-jdk-application-model/1.0.0/mirror-jdk-application-model-1.0.0.jar && \
    echo 'java -jar /usr/local/tomcat/alfresco-mmt/alfresco-mmt-6.0.jar install /usr/local/tomcat/amps/$1 /usr/local/tomcat/webapps/alfresco -force -nobackup' > install_amp.sh && chmod +x install_amp.sh && \
    ./install_amp.sh alfresco-promena-core-1.0.0.amp && \
    ./install_amp.sh alfresco-promena-connector-http-1.0.0.amp && \
    ./install_amp.sh alfresco-jvm-console-1.0.0.amp && \
    rm install_amp.sh

# alfresco-jvm-console
RUN echo $' \n\
grant { \n\
    permission java.security.AllPermission; \n\
}; ' >> /usr/local/tomcat/conf/catalina.policy