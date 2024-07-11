FROM openjdk:8-jdk-alpine

#作者
MAINTAINER ZincIon <1185911254xj@gmail.com>

#系统编码
ENV LANG=C.UTF-8 LC_ALL=C.UTF-8

# 挂载目录(容器启动时)
VOLUME /usr/local/neu6053
# 创建目录（该命令是容器运行时才会执行）
RUN mkdir -p /usr/local/neu6053
# 指定路径
WORKDIR /usr/local/neu6053
# 把当前路径的target目录下的jar包拷贝到镜像中，并重命名为water.jar
COPY ./target/NEPM6053.jar /usr/local/neu6053/NEPM6053.jar
ENTRYPOINT ["java","-jar","/usr/local/neu6053/NEPM6053.jar"]
#暴露8085端口
EXPOSE 8085