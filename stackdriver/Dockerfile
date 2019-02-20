FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package


FROM gcr.io/google-appengine/jetty
COPY --from=MAVEN_TOOL_CHAIN /tmp/target/gcptest*.war $JETTY_BASE/webapps/root.war
WORKDIR $JETTY_BASE
ENV JAVA_OPTS "-agentpath:/opt/cdbg/cdbg_java_agent.so -Dcom.google.cdbg.module=gcptest -Dcom.google.cdbg.version=1.0"
RUN java -jar $JETTY_HOME/start.jar --approve-all-licenses --add-to-startd=jmx,stats,hawtio && chown -R jetty:jetty $JETTY_BASE