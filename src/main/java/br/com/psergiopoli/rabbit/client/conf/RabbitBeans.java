package br.com.psergiopoli.rabbit.client.conf;

import br.com.psergiopoli.rabbit.client.util.RabbitExpcetion;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitBeans {

    private Logger logger = LoggerFactory.getLogger(RabbitBeans.class);

    @Bean(name="rabbitChannel")
    // @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Channel createChannel() {
        logger.info("bean called");
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("172.25.0.2");
            factory.setUsername("mqadmin");
            factory.setPassword("Admin123XX_");
            Connection connection = factory.newConnection();
            return connection.createChannel();
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new RabbitExpcetion(e.getMessage());
        }
    }

}
