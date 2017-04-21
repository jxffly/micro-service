# base image: oracle jdk8
FROM frolvlad/alpine-oraclejdk8:slim
# VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp。改步骤是可选的，如果涉及到文件系统的应用就很有必要了。/tmp目录用来持久化到 Docker 数据文件夹，因为 Spring Boot 使用的内嵌 Tomcat 容器默认使用/tmp作为工作目录
VOLUME /tmp
# Copy jar into image
ADD target/config-server-1.0.0.jar \
	 /emall/config-server-app.jar
ADD src/main/resources/bootstrap.yml /emall/config/bootstrap.yml
ADD src/main/resources/application-sandboxing.yml /emall/config/application.yml
ADD src/main/resources/config-server.jks /emall/config/config-server.jks
# EXPOSE 指令是声明运行时容器提供服务端口，这只是一个声明，在运行时并不会因为这个声明应用就会开启这个端口的服务。在 Dockerfile 中写入这样的声明有两个好处，一个是帮助镜像使用者理解这个镜像服务的守护端口，以方便配置映射；另一个用处则是在运行时使用随机端口映射时，也就是 docker run -P 时，会自动随机映射 EXPOSE 的端口。
EXPOSE 80 81
# You can use a RUN command to "touch" the jar file so that it has a file modification time (Docker creates all container files in an "unmodified" state by default). This actually isn’t important for the simple app that we wrote, but any static content (e.g. "index.html") would require the file to have a modification time.
RUN sh -c 'touch /emall/config-server-app.jar'
ENV JAVA_OPTS="-Djava.security.egd=file:/dev/./urandom -Dspring.config.location=file:/emall/config/bootstrap.yml,file:/emall/config/application.yml"
# 容器启动后需要执行的命令
# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
ENTRYPOINT [ "sh", "-c", \
	 "java $JAVA_OPTS -jar /emall/config-server-app.jar" ]