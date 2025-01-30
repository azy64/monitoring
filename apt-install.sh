apt update && \
apt install -y default-jre \
 default-jdk \
 maven \
 net-tools iputils-ping \
 && cd /home/monitoring && mvn spring-boot:run
