package tn.esprit.coco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.coco.entity.Car;
import tn.esprit.coco.entity.Ride;

public interface CarRepository extends JpaRepository<Car,Long> {
}
