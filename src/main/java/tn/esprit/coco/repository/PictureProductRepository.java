package tn.esprit.coco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.coco.entity.PictureProduct;

@Repository
public interface PictureProductRepository extends JpaRepository<PictureProduct, Long> {
}
