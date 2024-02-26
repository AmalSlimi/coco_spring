package tn.esprit.coco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.coco.entity.Room;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
