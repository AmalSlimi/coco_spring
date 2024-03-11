package tn.esprit.coco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.coco.entity.TripStop;
@Repository
public interface TripStopRepository extends JpaRepository<TripStop, Long> {
}