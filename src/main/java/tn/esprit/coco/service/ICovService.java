package tn.esprit.coco.service;

import tn.esprit.coco.entity.Image;

import java.util.List;

public interface ICovService {
    public List<Image> getImagesByCarID(Long carID);
}
