package org.github.sepulrator.rabbitmq.listener;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;

/**
 * Created by osman on 5.4.2017.
 */
public abstract class AbstractMessageListener<T> implements MessageListener {

    public abstract void processMessage(T message);

    @Override
    public void onMessage(Message queueMessage) {
        String jsonMessage = new String(queueMessage.getBody());
        ObjectMapper mapper = new ObjectMapper();
        T message = null;
        Class<T> typeOfT = (Class<T>)
                ((ParameterizedType)getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
        try {
            message = mapper.readValue(jsonMessage, typeOfT);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        processMessage(message);
    }
}
