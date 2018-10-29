package br.com.psergiopoli.rabbit.client.service;

import br.com.psergiopoli.rabbit.client.util.Message;
import br.com.psergiopoli.rabbit.client.util.RabbitExpcetion;
import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class RabbitmqService {

    private Logger logger;

    private Channel channel;

    @Autowired
    public RabbitmqService(@Qualifier("rabbitChannel") Channel channel) {
        this.channel = channel;
        this.logger = LoggerFactory.getLogger(RabbitmqService.class);
    }

    public Message sendMessage(String queue, String message) {

        try {
            this.channel.queueDeclare(queue, false, false, false, null);
            this.channel.basicPublish("", queue, null, message.getBytes());
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RabbitExpcetion(e.getMessage());
        }

        return new Message(" [x] Sent '" + message + "'");
    }

    public Message getMessage(String queue){

        try {
            this.channel.queueDeclare(queue, false, false, false, null);

            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope,
                                           AMQP.BasicProperties properties, byte[] body)
                        throws IOException {
                    String message = new String(body, "UTF-8");
                    logger.info(" [x] Received '" + message + "'");
                }
            };

            this.channel.basicConsume(queue, true, consumer);

        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new RabbitExpcetion(e.getMessage());
        }


        return new Message(" [x] Receiving message from queue: "+queue+", check log");
    }
}
