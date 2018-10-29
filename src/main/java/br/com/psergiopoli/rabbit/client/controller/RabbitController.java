package br.com.psergiopoli.rabbit.client.controller;

import br.com.psergiopoli.rabbit.client.service.RabbitmqService;
import br.com.psergiopoli.rabbit.client.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {

    private RabbitmqService rabbitmqService;

    @Autowired
    public RabbitController(RabbitmqService rabbitmqService) {
        this.rabbitmqService = rabbitmqService;
    }


    @RequestMapping(value = "/{queue}/{value}", method = RequestMethod.GET)
    public ResponseEntity<Message> sendMessage(@PathVariable(value = "queue") String queue, @PathVariable(value = "value") String value) {
        return new ResponseEntity<>(rabbitmqService.sendMessage(queue,value), HttpStatus.OK);
    }

    @RequestMapping(value = "/{queue}", method = RequestMethod.GET)
    public ResponseEntity<Message> getMessage(@PathVariable(value = "queue") String queue) {
        return new ResponseEntity<>(rabbitmqService.getMessage(queue), HttpStatus.OK);
    }


}
