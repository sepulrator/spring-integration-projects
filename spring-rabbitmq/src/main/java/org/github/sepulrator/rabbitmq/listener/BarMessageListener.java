package org.github.sepulrator.rabbitmq.listener;

import org.github.sepulrator.rabbitmq.message.Bar;

/**
 * Created by osman on 4.4.2017.
 */
public class BarMessageListener extends AbstractMessageListener<Bar> {

    @Override
    public void processMessage(Bar message) {
        System.out.println(message.toString());
    }

}
