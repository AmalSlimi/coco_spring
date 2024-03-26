package tn.esprit.coco.serviceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coco.entity.Subscription;
import tn.esprit.coco.entity.SubscriptionStatus;
import tn.esprit.coco.entity.Trip;
import tn.esprit.coco.entity.User;
import tn.esprit.coco.repository.StopRepository;
import tn.esprit.coco.repository.SubscriptionRepository;
import tn.esprit.coco.repository.UserRepository;
import tn.esprit.coco.service.IStopServices;
import tn.esprit.coco.service.ISubscriptionServices;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j

public class SubscriptionServicesImpl implements ISubscriptionServices{
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public Subscription addSubscription(Long userId, Subscription subscription) {

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setUser(user);

        Subscription savedSubscription = subscriptionRepository.save(subscription);


        String qrCodeData = generateQrCodeData(savedSubscription);


        savedSubscription.setQrCodeData(qrCodeData);
        savedSubscription.setSubscriptionPrice(savedSubscription.getRemainingTrips()*2);


        return subscriptionRepository.save(savedSubscription);
    }

    @Override
    public List<Subscription> getAllSubscription() {
        return (List<Subscription>) subscriptionRepository.findAll();
    }


    private String generateQrCodeData(Subscription subscription) {
        String qrCodeData = "Subscription ID: " + subscription.getId() + "\n"
                + "Remaining Trips: " + subscription.getRemainingTrips() + "\n";

        return qrCodeData;
    }

}
