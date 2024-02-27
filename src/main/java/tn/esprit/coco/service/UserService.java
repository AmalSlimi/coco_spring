package tn.esprit.coco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coco.entity.CategoryProduct;
import tn.esprit.coco.entity.User;
import tn.esprit.coco.repository.UserRepository;
import tn.esprit.coco.serviceImp.MarketPlaceImpl;
import tn.esprit.coco.serviceImp.UserImpl;

import java.util.List;

@Service
public class UserService implements UserImpl {
    @Autowired
    private UserRepository userRepository;


    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


}
