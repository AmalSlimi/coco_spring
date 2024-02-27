package tn.esprit.coco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.coco.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
