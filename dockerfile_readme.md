#E-commerce Workshop Dockerfile Instructions

.env_docker

ASTRA_DB_ID=f3a624eb-5e69-40d1-928a-58a84581368f
ASTRA_DB_REGION=us-east1
ASTRA_DB_APP_TOKEN=AstraCS:EcQSUgBLAHBLAHYOURTOKENHEREbc3a6c
ASTRA_DB_KEYSPACE=ecommerce
ASTRA_STREAM_TENANT=ecommerce-yourtenantname
ASTRA_STREAM_URL=pulsar+ssl://pulsar-gcp-uscentral1.streaming.datastax.com:6651
ASTRA_STREAM_TOKEN=eyJhbGciOiBLAHBLAHYOURTOKENHEREbzHw8ES4a6nl3ExGaHg
GOOGLE_CLIENT_ID=811509916831-ccit9nBLAHBLAHi4tng8ng13vk7.apps.googleusercontent.com
GOOGLE_CLIENT_SECRET=GOCSPX-grhBLAHBLAHSECRETSmq3

docker build -t ecom-demo .
docker run --env-file .env_docker --add-host api.astra.datastax.com:13.58.80.117 -p 8080:8080 ecom-demo
