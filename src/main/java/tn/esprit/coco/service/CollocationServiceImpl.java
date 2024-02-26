package tn.esprit.coco.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coco.dto.Dto.CategoryDto;
import tn.esprit.coco.dto.Dto.SubCategoryDto;
import tn.esprit.coco.entity.Accommodation;
import tn.esprit.coco.entity.Category;
import tn.esprit.coco.entity.PhotoAccommodation;
import tn.esprit.coco.entity.SubCategory;
import tn.esprit.coco.repository.AccommodationRepository;
import tn.esprit.coco.repository.CategoryRepository;
import tn.esprit.coco.repository.PhotoAccommodationRepository;
import tn.esprit.coco.repository.SubCategoryRepository;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class CollocationServiceImpl implements CollocationService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    SubCategoryRepository subCategoryRepository;
    @Autowired
    AccommodationRepository accommodationRepository;
    @Autowired
    PhotoAccommodationRepository photoAccommodationRepository;
    @Value("${user.photos.path}")
    String  dossierphoto;
    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll()  ; }

    @Override
    public Category AddCategory(CategoryDto categoryDto) {
        Category category=Category.builder()
                .categoryTitle(categoryDto.getCategoryTitle())
                .categoryDescription(categoryDto.getCategoryDescription())
                .build();

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long id, CategoryDto newcat) {
        Category category= categoryRepository.findById(id).get();
        category.setCategoryID(id);
        category.setCategoryTitle(newcat.getCategoryTitle());
        category.setCategoryDescription(newcat.getCategoryDescription());
        return categoryRepository.save(category);
    }

    @Override
    public void deletecategory(Long id) {
        categoryRepository.deleteById(id);

    }

    @Override
    public List<SubCategory> getListSubCategory(Long id) {
        return categoryRepository.findById(id).get().getSubCategories().stream().toList();

    }

    @Override
    public SubCategory addSubCategory(SubCategoryDto subCategoryDto) {
        SubCategory  subCategory= SubCategory.builder().subCategoryTitle(subCategoryDto.getSubCategoryTitle())
                .subCategoryDescription(subCategoryDto.getSubCategoryDescription())
                .category(categoryRepository.findById(subCategoryDto.getCategory()).get())
                .build();
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public List<SubCategory> getAllSubCategory() {
        return subCategoryRepository.findAll();
    }

    @Override
    public void deleteSubCategory(Long id) {
        subCategoryRepository.deleteById(id);
    }

    @Override
    public SubCategory updateSubCategory(Long id, SubCategoryDto newcat) {
        SubCategory  subCategory= SubCategory.builder().subCategoryTitle(newcat.getSubCategoryTitle())

                .subCategoryDescription(newcat.getSubCategoryDescription())
                .category(categoryRepository.findById(newcat.getCategory()).get())
                .subCategoryID(id)
                .build();

        return subCategoryRepository.save(subCategory);
    }

    @Override
    public Accommodation AddAccommodation(String adres, String local, String rules, float rente, int nbrrooom, MultipartFile multipartFile) throws IOException {
        Accommodation accommodation=Accommodation.builder()
                .address(adres)
                .rules(rules)
                .rent_price(rente)
                .localisation(local)
                .numberOfRoom(nbrrooom)
                .build();
        Accommodation save = accommodationRepository.save(accommodation);


        Path path= Paths.get(dossierphoto);
        if(!Files.exists(path)){
            Files.createDirectory(path);
        }
        Files.write(Path.of(dossierphoto,multipartFile.getOriginalFilename()),multipartFile.getBytes());
        PhotoAccommodation photoAccommodation=PhotoAccommodation.builder().accommodation(save)
                .title(multipartFile.getOriginalFilename())
                .path(dossierphoto+"/"+multipartFile.getOriginalFilename())
                .build();
        photoAccommodationRepository.save(photoAccommodation);


        return accommodation;    }

    @Override
    public byte[] getPhotoAccommodation(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Accommodation not found for id: " + id));
        Optional<PhotoAccommodation> photoAccommodationOptional = accommodation.getPhotoAccommodations().stream()
                .filter(c -> c.getAccommodation().getAccommodationID().equals(id))
                .findFirst();

        if (photoAccommodationOptional!=null) {
            try {
                return Files.readAllBytes(Path.of(photoAccommodationOptional.get().getPath()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("No photo found for accommodation id: " + id);
        }
    }



    @Override
    public List<Accommodation> getAllAccommodatiom() {
        return  accommodationRepository.findAll();
    }

    @Override
    public Accommodation getAccommodation(Long id) {
        return  accommodationRepository.findById(id).get();
    }

    @Override
    public void deleteAccommodation(Long id) {
        accommodationRepository.deleteById(id);
    }

    @Override
    public Accommodation updateAccommodation(Long id, String adres, String local, String rules, float rente, int nbrrooom, MultipartFile multipartFile) throws IOException {
        Accommodation accommodation=this.getAccommodation(id);
        accommodation.setAccommodationID(id);
        accommodation.setAddress(adres);
        accommodation.setLocalisation(local);
        accommodation.setNumberOfRoom(nbrrooom);
        accommodation.setRent_price(rente);
        accommodation.setRules(rules);

        Accommodation save = accommodationRepository.save(accommodation);


        Path path= Paths.get(dossierphoto);
        if(!Files.exists(path)){
            Files.createDirectory(path);
        }
        Files.write(Path.of(dossierphoto,multipartFile.getOriginalFilename()),multipartFile.getBytes());
        PhotoAccommodation photoAccommodation= photoAccommodationRepository.findByAccommodation_AccommodationID(save.getAccommodationID());
        photoAccommodation.setPath(dossierphoto+"/"+multipartFile.getOriginalFilename());
        photoAccommodationRepository.save(photoAccommodation);


        return accommodation;
    }



}
