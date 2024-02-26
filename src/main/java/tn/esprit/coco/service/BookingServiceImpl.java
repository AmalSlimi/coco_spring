package tn.esprit.coco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coco.entity.Booking;
import tn.esprit.coco.repository.AccommodationRepository;
import tn.esprit.coco.repository.BookingRepository;
import tn.esprit.coco.repository.RoomRepository;

import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Override
    public Booking addBooking(Booking booking) {
        Booking booking1=Booking.builder()
                .bookingDate(new Date())
                .accommodations(accommodationRepository.findById(booking.getAccommodationID()).get())
                .roomMateID(booking.getRoomMateID())
                .status("encours").build();
        return bookingRepository.save(booking1);
    }

    @Override
    public List<Booking> getAllBooking() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking getBooking(Long id) {
        return bookingRepository.findById(id).get();
    }

    @Override
    public void deletBooking(Long id) {
        bookingRepository.deleteById(id);

    }

    @Override
    public Booking upadate(Long id, Booking booking) {
        Booking booking1= bookingRepository.findById(id).get();
        booking1.setBookingID(id);
        booking1.setBookingDate(new Date());
        booking1.setAccommodations(accommodationRepository.findById(booking1.getAccommodationID()).get());
        booking1.setStatus("encours");
        Booking save = bookingRepository.save(booking1);

        return save;
    }
}
