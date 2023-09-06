package com.ahmedukamel.hms.service;

import com.ahmedukamel.hms.model.Room;
import com.ahmedukamel.hms.model.Type;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ahmedukamel.hms.model.Status.*;


@Service
public class RoomService {
    public static final List<Room> ROOMS = new ArrayList<>(List.of(
            Room.builder().number(100).status(AVAILABLE).type(Type.builder().name("Single Room").price(150).build()).build(),
            Room.builder().number(350).status(AVAILABLE).type(Type.builder().name("Single Room").price(150).build()).build(),
            Room.builder().number(360).status(AVAILABLE).type(Type.builder().name("Double Room").price(350).build()).build(),
            Room.builder().number(520).status(AVAILABLE).type(Type.builder().name("Triple Room").price(400).build()).build()
    ));
    public void add(Room room) {
        ROOMS.add(room);
    }

    public List<Room> getAll() {
        return ROOMS;
    }

    public void replace(Integer oldRoomNumber, Room newRoom) {
        Room oldRoom = ROOMS.stream().filter(room -> room.getNumber() == oldRoomNumber).findFirst().get();
        ROOMS.remove(oldRoom);
        ROOMS.add(newRoom);
    }

    public Room findByNumber(int number) {
        return ROOMS.stream().filter(room -> room.getNumber() == number).findFirst().orElseThrow();
    }
}
