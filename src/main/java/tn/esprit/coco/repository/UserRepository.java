package tn.esprit.coco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.coco.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
