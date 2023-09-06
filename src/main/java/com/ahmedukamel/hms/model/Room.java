package com.ahmedukamel.hms.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Room implements Prototype {
    private int number;
    private Status status;
    private Type type;

    @Override
    public Room shallowClone() {
        return Room.builder().number(this.number).status(this.status).type(this.type).build();
    }

    @Override
    public Room deepClone() {
        return Room.builder().number(this.number).status(this.status).type(this.type.deepClone()).build();
    }
}
