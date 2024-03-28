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
        int remainingTrips = savedSubscription.getRemainingTrips();

// Récupérer le nombre de TripStop choisi par l'utilisateur
        int selectedTripStops = savedSubscription.getTripStops(); // Supposons que cette propriété existe dans la classe Subscription

// Déterminer le prix par TripStop en fonction du choix de l'utilisateur
        double pricePerTripStop;
        if (selectedTripStops <= 3) {
            pricePerTripStop = 1.0;
        } else if (selectedTripStops <= 5) {
            pricePerTripStop = 1.5;
        } else {
            pricePerTripStop = 2.0;
        }

// Calculer le prix total
        double totalSubscriptionPrice = remainingTrips * pricePerTripStop;

// Définir le prix de l'abonnement
        savedSubscription.setSubscriptionPrice(totalSubscriptionPrice);



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
