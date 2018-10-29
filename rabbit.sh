docker-compose -f docker-compose-rabbit.yml down
docker-compose -f docker-compose-rabbit.yml up -d
docker inspect rabbitmq1 | grep IPAddress