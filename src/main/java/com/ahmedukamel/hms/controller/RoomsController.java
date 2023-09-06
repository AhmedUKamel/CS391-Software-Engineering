package com.ahmedukamel.hms.controller;


import com.ahmedukamel.hms.model.Room;
import com.ahmedukamel.hms.model.Status;
import com.ahmedukamel.hms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.ahmedukamel.hms.model.Status.*;


@Controller
@RequiredArgsConstructor
@RequestMapping("rooms")
public class RoomsController {
    private final RoomService roomService;
    private int id;

    @GetMapping("all")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("logged", SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null);
        return "/rooms";
    }

    @GetMapping("deep-clone/{number}")
    public String deepCloneRoom(@PathVariable("number") Integer number) {
        roomService.add(roomService.findByNumber(number).deepClone());
        return "redirect:/rooms/all";
    }

    @GetMapping("shallow-clone/{number}")
    public String shallowCloneRoom(@PathVariable("number") Integer number) {
        roomService.add(roomService.findByNumber(number).shallowClone());
        return "redirect:/rooms/all";
    }

    @GetMapping("edit/{number}")
    public String editRoomPage(@PathVariable("number") Integer number, Model model) {
        Room room = roomService.findByNumber(number);
        id = number;
        model.addAttribute("room", room);
        model.addAttribute("status", List.of(DiRTY, AVAILABLE, DISABLED, BUSY));
        model.addAttribute("logged", SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null);
        return "/edit-room";
    }

    @GetMapping("edit")
    public String editRoom(@RequestParam("number") Integer number, @RequestParam("price") Double price, @RequestParam("typeName") String typeName, @RequestParam("statusName") String statusName) {
        Room room = roomService.findByNumber(id);
        room.setNumber(number);
        room.getType().setName(typeName);
        room.getType().setPrice(price);
        room.setStatus(Status.valueOf(statusName));
        return "redirect:/rooms/all";
    }
}
