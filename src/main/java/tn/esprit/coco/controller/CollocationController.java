package tn.esprit.coco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coco.dto.Dto.CategoryDto;
import tn.esprit.coco.dto.Dto.SubCategoryDto;
import tn.esprit.coco.entity.*;
import tn.esprit.coco.service.BookingService;
import tn.esprit.coco.service.CollocationService;
import tn.esprit.coco.service.RoomService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/collocation")
public class CollocationController {
    @Autowired
    CollocationService collocationService;
    @Autowired
    RoomService roomService;
    @Autowired
    BookingService bookingService;

    //  **************** Category *******************
    @PostMapping("/add")
    public Category AddCategoru(@RequestBody CategoryDto categoryDto){
        return  collocationService.AddCategory(categoryDto);
    }
    @DeleteMapping("/delet/{id}")
    public void deletCat(@PathVariable Long id) {
        collocationService.deletecategory(id);
    }
    @GetMapping("/all")
    public List<Category> getll(){
        return  collocationService.getAll();
    }

    @PutMapping("/update/{id}")
    public Category update( @PathVariable Long id,@RequestBody CategoryDto newcat){
        return  collocationService.update(id, newcat);
    }





    //  **************** SubCategory *******************
    @PostMapping("/addsub")
    public SubCategory AddSubCategoru( @RequestBody SubCategoryDto subCategoryDto){
        return  collocationService.addSubCategory(subCategoryDto);
    }
    @GetMapping("/allsub")
    public List<SubCategory> getallsub(){
        return  collocationService.getAllSubCategory();
    }
    @PutMapping("/updatee/{id}")
    public SubCategory updateSubCategory( @PathVariable Long id, @RequestBody SubCategoryDto newcat){
        return  collocationService.updateSubCategory(id, newcat);
    }

    @DeleteMapping("/deleteSub/{id}")
    public void deleteSubCategory(@PathVariable Long id){
        collocationService.deleteSubCategory(id);
    }

    @GetMapping("/getListSub/{id}")
    public List<SubCategory> getListSubCategory(@PathVariable Long id){
        return collocationService.getListSubCategory(id);
    }


    //  **************** Accommodation *******************
    @PostMapping("/addacc")
    public Accommodation AddAccommodation(@RequestParam("a") String adres, @RequestParam("l") String local,@RequestParam("re") String rules,  @RequestParam("r") float rente, @RequestParam("n") int nbrrooom,  @RequestParam("f") MultipartFile multipartFile) throws IOException {
        return  collocationService.AddAccommodation(adres, local, rules, rente, nbrrooom, multipartFile) ;
    }
    @PutMapping("/updateacc/{id}")
    public  Accommodation updateAccomdation(@PathVariable Long id, @RequestParam("a") String adres, @RequestParam("l") String local,@RequestParam("re") String rules,  @RequestParam("r") float rente, @RequestParam("n") int nbrrooom,  @RequestParam("f") MultipartFile multipartFile) throws IOException {
        return  collocationService.updateAccommodation(id, adres, local, rules, rente, nbrrooom, multipartFile);
    }

    @GetMapping("/getacc/{id}")
    public Accommodation getAccommandation(Long id){
        return  collocationService.getAccommodation(id);
    }

    @GetMapping("/allacc")
    public List<Accommodation> getAllAccomdation(){
        return  collocationService.getAllAccommodatiom();
    }

    @DeleteMapping("/deletacc/{id}")
    public  void deletAccommdatio( @PathVariable Long id){
        collocationService.deleteAccommodation(id);
    }

    @GetMapping(value = "/get/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public  byte[] getPhotoAccomndation(@PathVariable Long id){
        return  collocationService.getPhotoAccommodation(id);
    }







    //  **************** Room *******************
    @PostMapping("/addroom")
    public Room AddRoom( @RequestParam("rt") String roomType,  @RequestParam("r") float rent, @RequestParam("a") String amenities,  @RequestParam("rd") String roomDetails,  @RequestParam("id") Long accommodationID, @RequestParam("file") MultipartFile multipartFile) throws IOException{
        return  roomService.AddRoom(roomType, rent, amenities, roomDetails, accommodationID, multipartFile);
    }
    @GetMapping("/allroom")
    public  List<Room> getAll(){
        return  roomService.getAllRoom();
    }
    @DeleteMapping("/deletrrom/{id}")
    public  void deleteRoom( @PathVariable Long id){
        roomService.deleteRoom(id);
    }
    @GetMapping("/getroom/{id}")
    public Room getRoom( @PathVariable Long id){
        return  roomService.getRoom(id);
    }

    @GetMapping(value = "/gettt/{id}",produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getPhotoRoom( @PathVariable Long id){
        return  roomService.getPhotoRoom(id);
    }





    //  **************** Booking *******************
    @PostMapping("/addbook")
    public Booking addBooking(@RequestBody Booking booking){
        return  bookingService.addBooking(booking);
    }
    @GetMapping("/allbook")
    public  List<Booking> getAllBoooking(){
        return  bookingService.getAllBooking();
    }
    @DeleteMapping("/deletbook/{id}")
    public void dletebooking( @PathVariable Long id){
        bookingService.deletBooking(id);
    }
    @PutMapping("/updatebook/{id}")
    public Booking upafteBooking(@PathVariable Long id, @RequestBody Booking booking){
        return  bookingService.upadate(id, booking);
    }
}


