FROM openjdk:8u292-jre

COPY target/digital-saler-inventory-simulator/ /opt/digital-saler-inventory-simulator/
WORKDIR /opt/digital-saler-inventory-simulator/
ENTRYPOINT ["/bin/sh", "run.sh"]
