package tn.esprit.coco.serviceImp;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coco.entity.Car;
import tn.esprit.coco.entity.Image;
import tn.esprit.coco.entity.Security;
import tn.esprit.coco.repository.CarRepository;
import tn.esprit.coco.service.ICovService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@AllArgsConstructor
public class ICovServiceImpl implements ICovService {
    @Autowired
    private CarRepository carRepository;
    @Override
    public List<Image> getImagesByCarID(Long carID) {
        Optional<Car> optionalCar = carRepository.findById(carID);
        List<Image> images = new ArrayList<>();

        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            images = car.getImages();
            for (Image image : images) {
                System.out.println("Format de l'image : " + image.getFormat());
            }
        }
        System.out.println("Format de l'image : ");
        return images;
    }
}
