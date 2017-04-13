package org.github.sepulrator.rabbitmq.message;

import java.io.Serializable;

/**
 * Created by osman on 1.4.2017.
 */
public class Foo implements Serializable {
    private int id;

    public int getId() {
        return id;
    }

    public Foo setId(int id) {
        this.id = id;
        return this;
    }
}
