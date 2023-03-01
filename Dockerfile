FROM maven:3-eclipse-temurin-17

# set Bash as the default shell
SHELL ["/bin/bash", "--login", "-c"]

ARG ASTRA_DB_ID=${ASTRA_DB_ID}
ARG ASTRA_DB_REGION=${ASTRA_DB_REGION}
ARG ASTRA_DB_APP_TOKEN=${ASTRA_DB_APP_TOKEN}
ARG ASTRA_DB_KEYSPACE=ecommerce
ARG ASTRA_STREAM_TENANT=${ASTRA_STREAM_TENANT}
ARG ASTRA_STREAM_URL=${ASTRA_STREAM_URL}
ARG ASTRA_STREAM_TOKEN=${ASTRA_STREAM_TOKEN}
ARG GOOGLE_CLIENT_ID=${GOOGLE_CLIENT_ID}
ARG GOOGLE_CLIENT_SECRET=${GOOGLE_CLIENT_SECRET}

# Node.js args
ARG NODE_VERSION=14.18.2
ARG NODE_PACKAGE=node-v$NODE_VERSION-linux-x64
ARG NODE_HOME=/opt/$NODE_PACKAGE

ADD . /app/

# set Node.js working directory
WORKDIR /app/ui/

RUN apt-get update && apt-get install -y \
  ca-certificates \
  curl

ENV NODE_PATH $NODE_HOME/lib/node_modules
ENV PATH $NODE_HOME/bin:$PATH

# install Node.js binary
RUN curl https://nodejs.org/dist/v$NODE_VERSION/$NODE_PACKAGE.tar.gz | tar -xzC /opt/

# output Java version
RUN java -version

# build web UI
RUN npm install
RUN npm run build

EXPOSE 8080

CMD mvn spring-boot:run -f /app/backend/pom.xml
