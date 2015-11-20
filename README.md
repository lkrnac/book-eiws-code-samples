[ ![Codeship Status for lkrnac/book-eiws-code-samples](https://codeship.com/projects/3d6d0a40-ae3f-0132-7ed9-2ecd9a04cc80/status?branch=master)](https://codeship.com/projects/68847)

# More information
http://lkrnac.net/blog/2015/10/enterprise-spring-examples-and-integration-tests

# Build
    cd 0000-examples-parent
    mvn clean install
    cd ../0000-examples
    mvn clean install

# Possible first time build error
Error message:

    [ERROR] Failed to execute goal org.hornetq:hornetq-maven-plugin:1.2.0:start (start) on project 0501-jms11-jndi: Execution start of goal org.hornetq:hornetq-maven-plugin:1.2.0:start failed: A required class was missing while executing org.hornetq:hornetq-maven-plugin:1.2.0:start: org/slf4j/LoggerFactory

Just continue build where it ended:

    mvn clean install -rf :0501-jms11-jndi

