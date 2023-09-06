package com.ahmedukamel.hms.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Type implements Prototype {
    private double price;
    private String name;

    @Override
    public Type shallowClone() {
        return Type.builder().price(this.price).name(this.name).build();
    }

    @Override
    public Type deepClone() {
        return this.shallowClone();
    }
}
