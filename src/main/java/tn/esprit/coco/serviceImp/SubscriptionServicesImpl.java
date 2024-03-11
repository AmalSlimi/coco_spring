package tn.esprit.coco.serviceImp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coco.entity.Subscription;
import tn.esprit.coco.entity.SubscriptionStatus;
import tn.esprit.coco.entity.User;
import tn.esprit.coco.repository.StopRepository;
import tn.esprit.coco.repository.SubscriptionRepository;
import tn.esprit.coco.repository.UserRepository;
import tn.esprit.coco.service.IStopServices;
import tn.esprit.coco.service.ISubscriptionServices;

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
        // 1. Récupérer l'utilisateur à partir de son ID
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // 2. Mettre à jour l'abonnement avec les informations fournies par l'utilisateur
        // Par exemple, vous pouvez définir le statut de l'abonnement comme actif
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setUser(user);

        // 3. Effectuer les opérations nécessaires pour le paiement (non implémenté ici)

        // 4. Sauvegarder l'abonnement dans la base de données
        Subscription savedSubscription = subscriptionRepository.save(subscription);

        // 5. Générer les données du code QR en fonction des informations de l'abonnement
        String qrCodeData = generateQrCodeData(savedSubscription);

        // 6. Mettre à jour l'abonnement avec les données du code QR
        savedSubscription.setQrCodeData(qrCodeData);

        // 7. Sauvegarder à nouveau l'abonnement avec les données du code QR
        return subscriptionRepository.save(savedSubscription);
    }


    private String generateQrCodeData(Subscription subscription) {
        String qrCodeData = "Subscription ID: " + subscription.getId() + "\n"
                + "Remaining Trips: " + subscription.getRemainingTrips() + "\n";

        return qrCodeData;
    }

}
