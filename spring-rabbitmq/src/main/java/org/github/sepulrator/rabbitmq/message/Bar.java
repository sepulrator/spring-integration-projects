package org.github.sepulrator.rabbitmq.message;

import java.io.Serializable;

/**
 * Created by osman on 4.4.2017.
 */
public class Bar implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public Bar setId(int id) {
        this.id = id;
        return this;
    }
}
