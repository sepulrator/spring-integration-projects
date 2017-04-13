package org.github.sepulrator.rabbitmq.listener;

import org.codehaus.jackson.map.ObjectMapper;
import org.github.sepulrator.rabbitmq.message.Foo;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;

/**
 * Created by osman on 1.4.2017.
 */

public class FooMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        String jsonMessage = new String(message.getBody());
        ObjectMapper mapper = new ObjectMapper();
//JSON from file to Object
        Foo foo = null;
        try {
            foo = mapper.readValue(jsonMessage, Foo.class);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(foo.toString());
    }
}
