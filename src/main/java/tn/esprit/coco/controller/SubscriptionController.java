package tn.esprit.coco.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.coco.entity.Subscription;
import tn.esprit.coco.entity.Trip;
import tn.esprit.coco.serviceImp.SubscriptionServicesImpl;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/subscriptions")

public class SubscriptionController {
    @Autowired
    private SubscriptionServicesImpl subscriptionService;

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addSubscription(@PathVariable Long userId, @RequestBody Subscription subscription) {
        Subscription addedSubscription = subscriptionService.addSubscription(userId, subscription);
        if (addedSubscription != null) {
            return ResponseEntity.ok(addedSubscription);
        } else {
            return ResponseEntity.badRequest().body("Failed to add subscription.");
        }
    }
    @GetMapping("/get-all")
    public List<Subscription> getAllSubscription(){
        return  subscriptionService.getAllSubscription();
    }

}
