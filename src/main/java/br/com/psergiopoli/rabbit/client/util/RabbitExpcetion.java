package br.com.psergiopoli.rabbit.client.util;

public class RabbitExpcetion extends RuntimeException {

    public RabbitExpcetion(String message) {
        super(message);
    }
}
