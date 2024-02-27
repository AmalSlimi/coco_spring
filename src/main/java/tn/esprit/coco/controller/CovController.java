package tn.esprit.coco.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.coco.entity.*;
import tn.esprit.coco.repository.*;
import tn.esprit.coco.service.ICovService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class CovController {
    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private FavoriteRepository favoriteRepository;
    @Autowired
    private SecurityRepository securityRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ICovService icovService;

    /****************************** Ride Controller *************************************/

    @PostMapping("/addRide")
    public ResponseEntity<Ride> addRide ( @RequestBody Ride ride){
        Ride rideobj = rideRepository.save(ride);
        return  new ResponseEntity<>(rideobj, HttpStatus.OK);
    }
    @PostMapping("/updateRide/{rideID}")
    public ResponseEntity<Ride> updateRide(@PathVariable Long rideID, @RequestBody Ride ride) {

        //covService.updateRide(ride);
        Optional<Ride> oldRide=rideRepository.findById(rideID);
        if (oldRide.isPresent()){
            Ride updatedRide=oldRide.get();
            updatedRide.setArrivalDate(ride.getArrivalDate());
            updatedRide.setDriver(ride.getDriver());
            updatedRide.setPrice(ride.getPrice());
            updatedRide.setDepartureDate(ride.getDepartureDate());
            updatedRide.setStartLocation(ride.getStartLocation());

            Ride rideobj= rideRepository.save(updatedRide);
            return  new ResponseEntity<>(rideobj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteRide/{rideID}")
    public ResponseEntity<HttpStatus> deleteRide(@PathVariable Long rideID) {
        rideRepository.deleteById(rideID);
        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("getRideById/{rideID}")
    public ResponseEntity<Ride> getRideById(@PathVariable Long rideID) {
        Optional<Ride> R1=rideRepository.findById(rideID);
        return R1.map(ride -> new ResponseEntity<>(ride, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAllRide")
    public ResponseEntity <List<Ride>> getAllRide() {
        try {
            List<Ride> rideList = new ArrayList<>(rideRepository.findAll());

            if (rideList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(rideList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /****************************** Car Controller *************************************/
    @PostMapping("/addCar")
    public ResponseEntity<Car> addRide ( @RequestBody Car car){
        Car carobj = carRepository.save(car);
        return  new ResponseEntity<>(carobj, HttpStatus.OK);
    }
    @PostMapping("/updateCar/{carID}")
    public ResponseEntity<Car> updateCar(@PathVariable Long carID, @RequestBody Car car) {

        //covService.updateRide(ride);
        Optional<Car> oldCar=carRepository.findById(carID);
        if (oldCar.isPresent()){
            Car updatedCar=oldCar.get();
            updatedCar.setComfort(car.getComfort());
            updatedCar.setRide(car.getRide());
            updatedCar.setModel(car.getModel());
            updatedCar.setImages(car.getImages());


            Car carobj= carRepository.save(updatedCar);
            return  new ResponseEntity<>(carobj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteCar/{carID}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long carID) {
        carRepository.deleteById(carID);
        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("getCarById/{carID}")
    public ResponseEntity<Car> getCarById(@PathVariable Long carID) {
        Optional<Car> R1=carRepository.findById(carID);
        return R1.map(car -> new ResponseEntity<>(car, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAllCar")
    public ResponseEntity <List<Car>> getAllCar() {
        try {
            List<Car> carList = new ArrayList<>(carRepository.findAll());

            if (carList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(carList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /****************************** Reservation Controller *******************************/
    @PostMapping("/addReservation")
    public ResponseEntity<Reservation> addReservation (@RequestBody Reservation reservation){
        Reservation carobj = reservationRepository.save(reservation);
        return  new ResponseEntity<>(carobj, HttpStatus.OK);
    }
    @PostMapping("/updateReservation/{reservationID}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long reservationID, @RequestBody Reservation reservation) {

        //covService.updateRide(ride);
        Optional<Reservation> oldReservation=reservationRepository.findById(reservationID);
        if (oldReservation.isPresent()){
            Reservation updatedReservation=oldReservation.get();
            updatedReservation.setReservationDate(reservation.getReservationDate());
            updatedReservation.setPassenger(reservation.getPassenger());
            updatedReservation.setPayment(reservation.getPayment());
            updatedReservation.setSecurity(reservation.getSecurity());
            updatedReservation.setRideR(reservation.getRideR());
            updatedReservation.setPassengerID(reservation.getPassengerID());


            Reservation carobj= reservationRepository.save(updatedReservation);
            return  new ResponseEntity<>(carobj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteReservation/{reservationID}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable Long reservationID) {
        reservationRepository.deleteById(reservationID);
        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("getReservationById/{reservationID}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long reservationID) {
        Optional<Reservation> R1=reservationRepository.findById(reservationID);
        return R1.map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAllReservation")
    public ResponseEntity <List<Reservation>> getAllReservation() {
        try {
            List<Reservation> reservationList = new ArrayList<>(reservationRepository.findAll());

            if (reservationList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(reservationList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /****************************** Payment Controller ******************************/
    @PostMapping("/addPayment")
    public ResponseEntity<Payment> addPayment (@RequestBody Payment payment){
        Payment carobj = paymentRepository.save(payment);
        return  new ResponseEntity<>(carobj, HttpStatus.OK);
    }
    @PostMapping("/updatePayment/{paymentID}")
    public ResponseEntity<Payment> updatePayment(@PathVariable Long paymentID, @RequestBody Payment payment) {

        //covService.updateRide(ride);
        Optional<Payment> oldPayment=paymentRepository.findById(paymentID);
        if (oldPayment.isPresent()){
            Payment updatedPayment=oldPayment.get();
            updatedPayment.setPaymentStatus(payment.getPaymentStatus());
            updatedPayment.setTypePayment(payment.getTypePayment());
            updatedPayment.setAmount(payment.getAmount());
            updatedPayment.setReservation(payment.getReservation());



            Payment carobj= paymentRepository.save(updatedPayment);
            return  new ResponseEntity<>(carobj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deletePayment/{paymentID}")
    public ResponseEntity<HttpStatus> deletePayment(@PathVariable Long paymentID) {
        paymentRepository.deleteById(paymentID);
        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("getPaymentById/{paymentID}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable Long paymentID) {
        Optional<Payment> R1=paymentRepository.findById(paymentID);
        return R1.map(reservation -> new ResponseEntity<>(reservation, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAllPayment")
    public ResponseEntity <List<Payment>> getAllPayment() {
        try {
            List<Payment> paymentList = new ArrayList<>(paymentRepository.findAll());

            if (paymentList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(paymentList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /****************************** Favorite Controller ******************************/
    @PostMapping("/addFavorite")
    public ResponseEntity<Favorite> addFavorite (@RequestBody Favorite favorite){
        Favorite carobj = favoriteRepository.save(favorite);
        return  new ResponseEntity<>(carobj, HttpStatus.OK);
    }
    @PostMapping("/updateFavorite/{favoriteID}")
    public ResponseEntity<Favorite> updateFavorite(@PathVariable Long favoriteID, @RequestBody Favorite favorite) {

        //covService.updateRide(ride);
        Optional<Favorite> oldFavorite=favoriteRepository.findById(favoriteID);
        if (oldFavorite.isPresent()){
            Favorite updatedFavorite=oldFavorite.get();
            updatedFavorite.setRide(favorite.getRide());
            updatedFavorite.setUser(favorite.getUser());

            Favorite carobj= favoriteRepository.save(updatedFavorite);
            return  new ResponseEntity<>(carobj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteFavorite/{favoriteID}")
    public ResponseEntity<HttpStatus> deleteFavorite(@PathVariable Long favoriteID) {
        favoriteRepository.deleteById(favoriteID);
        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("getFavoriteById/{favoriteID}")
    public ResponseEntity<Favorite> getFavoriteById(@PathVariable Long favoriteID) {
        Optional<Favorite> R1=favoriteRepository.findById(favoriteID);
        return R1.map(favorite -> new ResponseEntity<>(favorite, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAllFavorite")
    public ResponseEntity <List<Favorite>> getAllFavorite() {
        try {
            List<Favorite> favoriteList = new ArrayList<>(favoriteRepository.findAll());

            if (favoriteList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(favoriteList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /****************************** Image Controller ******************************/
    @PostMapping("/addImage")
    public ResponseEntity<Image> addImage (@RequestBody Image image){
        Image carobj = imageRepository.save(image);
        return  new ResponseEntity<>(carobj, HttpStatus.OK);
    }
    @PostMapping("/updateImage/{imageID}")
    public ResponseEntity<Image> updateImage(@PathVariable Long imageID, @RequestBody Image image) {

        //covService.updateRide(ride);
        Optional<Image> oldImage=imageRepository.findById(imageID);
        if (oldImage.isPresent()){
            Image updatedImage=oldImage.get();
            updatedImage.setCar(image.getCar());
            updatedImage.setPath(image.getPath());
            updatedImage.setFormat(image.getFormat());
            updatedImage.setDateAdded(image.getDateAdded());


            Image carobj= imageRepository.save(updatedImage);
            return  new ResponseEntity<>(carobj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteImage/{imageID}")
    public ResponseEntity<HttpStatus> deleteImage(@PathVariable Long imageID) {
        imageRepository.deleteById(imageID);
        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("getImageById/{imageID}")
    public ResponseEntity<Image> getImageById(@PathVariable Long imageID) {
        Optional<Image> R1=imageRepository.findById(imageID);
        return R1.map(image -> new ResponseEntity<>(image, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAllImage")
    public ResponseEntity <List<Image>> getAllImage() {
        try {
            List<Image> imageList = new ArrayList<>(imageRepository.findAll());

            if (imageList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(imageList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   /* @GetMapping("/images/{carId}")
    public ResponseEntity<List<Image>> getImagesByCarId(@PathVariable Long carId) {
        List<Image> images = icovService.getImagesByCarID(carId);
        if (!images.isEmpty()) {
            return ResponseEntity.ok(images);
        } else {
            return ResponseEntity.noContent().build();
        }
    }*/
   @GetMapping("/getImagesByCarID/{carID}")
   public List<Image> getImagesByCarID(@PathVariable Long carID) {
       return icovService.getImagesByCarID(carID);
   }

    /****************************** Security Controller ******************************/
    @PostMapping("/addSecurity")
    public ResponseEntity<Security> addSecurity (@RequestBody Security security){
        Security carobj = securityRepository.save(security);
        return  new ResponseEntity<>(carobj, HttpStatus.OK);
    }
    @PostMapping("/updateSecurity/{securityID}")
    public ResponseEntity<Security> updateSecurity(@PathVariable Long securityID, @RequestBody Security security) {

        //covService.updateRide(ride);
        Optional<Security> oldSecurity=securityRepository.findById(securityID);
        if (oldSecurity.isPresent()){
            Security updatedSecurity=oldSecurity.get();
            updatedSecurity.setReservation(security.getReservation());
            updatedSecurity.setIsActivated(security.getIsActivated());
            updatedSecurity.setSharedData(security.getSharedData());



            Security carobj= securityRepository.save(updatedSecurity);
            return  new ResponseEntity<>(carobj, HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/deleteSecurity/{securityID}")
    public ResponseEntity<HttpStatus> deleteSecurity(@PathVariable Long securityID) {
        securityRepository.deleteById(securityID);
        return  new ResponseEntity<>( HttpStatus.OK);
    }
    @GetMapping("getSecurityById/{securityID}")
    public ResponseEntity<Security> getSecurityById(@PathVariable Long securityID) {
        Optional<Security> R1=securityRepository.findById(securityID);
        return R1.map(security -> new ResponseEntity<>(security, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/getAllSecurity")
    public ResponseEntity <List<Security>> getAllSecurity() {
        try {
            List<Security> securityList = new ArrayList<>(securityRepository.findAll());

            if (securityList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(securityList    , HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
