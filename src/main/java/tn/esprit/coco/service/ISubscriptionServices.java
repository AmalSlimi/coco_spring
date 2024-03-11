package tn.esprit.coco.service;

import tn.esprit.coco.entity.Subscription;
import tn.esprit.coco.entity.TripStop;

public interface ISubscriptionServices {
    Subscription addSubscription(Long userId,  Subscription Subscription);
}
