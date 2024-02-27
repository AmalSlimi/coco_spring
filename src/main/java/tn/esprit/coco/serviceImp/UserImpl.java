package tn.esprit.coco.serviceImp;

import tn.esprit.coco.entity.CategoryProduct;
import tn.esprit.coco.entity.User;

import java.util.List;

public interface UserImpl {
    /////USER
    User createUser(User user);

    User getUserById(Long id);
    public List<User> getAllUsers();
}
