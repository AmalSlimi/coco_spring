package tn.esprit.coco.service;

import tn.esprit.coco.entity.Booking;

import java.awt.print.Book;
import java.util.List;

public interface BookingService {
    public Booking addBooking(Booking booking);
    public List<Booking> getAllBooking();
    public  Booking getBooking(Long id);
    public void deletBooking(Long id);
    public Booking upadate(Long id,Booking booking);
}
