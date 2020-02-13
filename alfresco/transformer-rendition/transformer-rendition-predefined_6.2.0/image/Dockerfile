FROM alfresco/alfresco-content-repository-community:6.2.0-ga

ARG TOMCAT_DIR=/usr/local/tomcat

USER root
RUN yum install -y wget && \
    wget -O $TOMCAT_DIR/amps/alfresco-promena-core-1.0.1.amp https://oss.sonatype.org/service/local/repositories/releases/content/pl/beone/promena/alfresco/module/alfresco-promena-core/1.0.1/alfresco-promena-core-1.0.1.amp && \
    wget -O $TOMCAT_DIR/amps/alfresco-promena-connector-activemq-1.0.1.amp https://oss.sonatype.org/service/local/repositories/releases/content/pl/beone/promena/alfresco/module/connector/alfresco-promena-connector-activemq/1.0.1/alfresco-promena-connector-activemq-1.0.1.amp && \
    wget -O $TOMCAT_DIR/amps/alfresco-promena-transformer-rendition_6.2.0-2.0.1.amp https://oss.sonatype.org/service/local/repositories/releases/content/pl/beone/promena/alfresco/module/transformer-rendition/alfresco-promena-transformer-rendition_6.2.0/2.0.1/alfresco-promena-transformer-rendition_6.2.0-2.0.1.amp && \
    wget -O $TOMCAT_DIR/amps/alfresco-promena-transformer-rendition-predefined-2.0.0.amp https://oss.sonatype.org/service/local/repositories/releases/content/pl/beone/promena/alfresco/module/transformer-rendition/alfresco-promena-transformer-rendition-predefined/2.0.0/alfresco-promena-transformer-rendition-predefined-2.0.0.amp && \
    wget -O $TOMCAT_DIR/amps/alfresco-jvm-console-1.0.0.amp https://github.com/Skotar/alfresco-jvm-console/releases/download/1.0.0/alfresco-jvm-console-1.0.0.amp && \
    wget -O $TOMCAT_DIR/webapps/alfresco/WEB-INF/lib/mirror-jdk-application-model-1.0.0.jar https://oss.sonatype.org/service/local/repositories/releases/content/pl/beone/promena/transformer/mirror-jdk-application-model/1.0.0/mirror-jdk-application-model-1.0.0.jar && \
    echo 'java -jar /usr/local/tomcat/alfresco-mmt/alfresco-mmt-6.0.jar install /usr/local/tomcat/amps/$1 /usr/local/tomcat/webapps/alfresco -force -nobackup' > install_amp.sh && chmod +x install_amp.sh && \
    ./install_amp.sh alfresco-promena-core-1.0.1.amp && \
    ./install_amp.sh alfresco-promena-connector-activemq-1.0.1.amp && \
    ./install_amp.sh alfresco-promena-transformer-rendition_6.2.0-2.0.1.amp && \
    ./install_amp.sh alfresco-promena-transformer-rendition-predefined-2.0.0.amp && \
    ./install_amp.sh alfresco-jvm-console-1.0.0.amp && \
    rm install_amp.sh

# Replacing original by new one with "*.layout" set to "org.apache.log4j.PatternLayout" ("org.alfresco.util.log.log4j.SanitizingPatternLayout" doesn't print new lines)
COPY log4j.properties $TOMCAT_DIR/webapps/alfresco/WEB-INF/classes/log4j.properties

# alfresco-jvm-console
RUN echo $' \n\
grant { \n\
    permission java.security.AllPermission; \n\
}; ' >> /usr/local/tomcat/conf/catalina.policy

USER ${USERNAME}