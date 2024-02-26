package tn.esprit.coco.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coco.entity.Room;

import java.io.IOException;
import java.util.List;

public interface RoomService {
    public Room AddRoom(String roomType,
                        float rent,
                        String amenities,
                        String roomDetails,
                        Long accommodationID, MultipartFile multipartFile) throws IOException;
    public List<Room> getAllRoom();
    public Room getRoom(Long id);
    public void deleteRoom(Long id);
    public Room updateRoom(Long id,Room room);

    byte[] getPhotoRoom(Long id);
}
