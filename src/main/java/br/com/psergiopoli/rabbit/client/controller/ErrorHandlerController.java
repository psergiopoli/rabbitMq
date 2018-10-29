package br.com.psergiopoli.rabbit.client.controller;

import br.com.psergiopoli.rabbit.client.util.Message;
import br.com.psergiopoli.rabbit.client.util.RabbitExpcetion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandlerController extends ResponseEntityExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(ErrorHandlerController.class);

    @ExceptionHandler(RabbitExpcetion.class)
    public final ResponseEntity<Message> handleUserNotFoundException(RabbitExpcetion ex) {
        Message message = new Message(ex.getMessage());
        logger.error("Handling error: "+ex.getMessage());
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
