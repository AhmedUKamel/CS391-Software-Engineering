package com.ahmedukamel.hms.model;

public interface Prototype {
    Object shallowClone();

    Object deepClone();
}
