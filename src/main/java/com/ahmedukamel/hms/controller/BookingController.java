package com.ahmedukamel.hms.controller;

import com.ahmedukamel.hms.model.Booking;
import com.ahmedukamel.hms.model.Room;
import com.ahmedukamel.hms.model.Status;
import com.ahmedukamel.hms.service.ApplicationUserService;
import com.ahmedukamel.hms.service.BookingService;
import com.ahmedukamel.hms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("bookings")
@RequiredArgsConstructor
public class BookingController {
    private final ApplicationUserService applicationUserService;
    private final RoomService roomService;
    private final BookingService bookingService;
    @GetMapping("all")
    public String getBookingPage(Model model) {
        model.addAttribute("bookings", bookingService.getAll());
        model.addAttribute("logged", SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null);
        return "bookings";
    }

    @GetMapping("new")
    public String getAddBookingPage(Model model) {
        model.addAttribute("guests", applicationUserService.getGuests());
        model.addAttribute("rooms", roomService.getAll());
        model.addAttribute("logged", SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null);
        return "/add-booking";
    }

    @GetMapping("check-out/{number}")
    public String checkOut(@PathVariable("number")Integer number) {
        Booking booking = bookingService.findByNumber(number);
        Room room = booking.getRoom();
        room.setStatus(Status.DiRTY);
        roomService.replace(room.getNumber(), room);
        bookingService.getAll().remove(booking);
        return "redirect:/bookings/all";
    }

    @GetMapping("add")
    public String addBookingPage(
            @RequestParam("Days")Integer days,
            @RequestParam("Guest")String username,
            @RequestParam("Room")Integer room) {
        Room selectedRoom = roomService.findByNumber(room);
        selectedRoom.setStatus(Status.BUSY);
        roomService.replace(room, selectedRoom);
        bookingService.add(Booking.builder().number(bookingService.getAll().size()+1).guest(applicationUserService.get(username)).days(days).room(selectedRoom).checkin(new Date()).build());
        return "redirect:/bookings/all";
    }
}
