package tn.esprit.coco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coco.entity.*;
import tn.esprit.coco.repository.AccommodationRepository;
import tn.esprit.coco.repository.RoomPhotoRepository;
import tn.esprit.coco.repository.RoomPhotoRepositru;
import tn.esprit.coco.repository.RoomRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomPhotoRepositru roomPhotoRepositru;
    @Value("${user.photos.path}")
    String  dossierphoto;
    @Autowired
    AccommodationRepository accommodationRepository;
    @Override
    public Room AddRoom(String roomType, float rent, String amenities, String roomDetails, Long accommodationID, MultipartFile multipartFile) throws IOException {
        Accommodation accommodation= accommodationRepository.findById(accommodationID).get();
        Room room=Room.builder()
                .roomDetails(roomDetails)
                .accommodationID(accommodationID)
                .roomType(RoomType.valueOf(roomType))
                .amenities(amenities)
                .accommodations(accommodation)
                .rent(rent).build();
        Room save = roomRepository.save(room);
        Path path= Paths.get(dossierphoto);
        if(!Files.exists(path)){
            Files.createDirectory(path);
        }
        Files.write(Path.of(dossierphoto,multipartFile.getOriginalFilename()),multipartFile.getBytes());
        RoomPhoto photoAccommodation=RoomPhoto.builder().room(save)
                .title(multipartFile.getOriginalFilename())
                .path(dossierphoto+"/"+multipartFile.getOriginalFilename())
                .build();
        roomPhotoRepositru.save(photoAccommodation);


        return save;
    }

    @Override
    public List<Room> getAllRoom() {
        return roomRepository.findAll();
    }

    @Override
    public Room getRoom(Long id) {
        return roomRepository.findById(id).get();
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }



    @Override
    public Room updateRoom(Long id, Room room) {
        return null;
    }
    @Override
    public byte[] getPhotoRoom(Long id) {
        Room accommodation = roomRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Accommodation not found for id: " + id));
        Optional<RoomPhoto> photoAccommodationOptional = accommodation.getRoomPhotos().stream()
                .filter(c -> c.getRoom().getRoomID().equals(id))
                .findFirst();

        if (photoAccommodationOptional!=null) {
            try {
                return Files.readAllBytes(Path.of(photoAccommodationOptional.get().getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("No photo found for accommodation id: " + id);
        }
    }
}
