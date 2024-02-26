package tn.esprit.coco.service;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.coco.dto.Dto.CategoryDto;
import tn.esprit.coco.dto.Dto.SubCategoryDto;
import tn.esprit.coco.entity.Accommodation;
import tn.esprit.coco.entity.Category;
import tn.esprit.coco.entity.SubCategory;


import java.io.IOException;
import java.util.List;

public interface CollocationService {
    List<Category> getAll();
    public Category AddCategory(CategoryDto categoryDto);
    public Category update(Long id,CategoryDto newcat);
    public void deletecategory(Long id);
    public List<SubCategory> getListSubCategory(Long id);
    SubCategory addSubCategory(SubCategoryDto subCategoryDto);
    public  List<SubCategory> getAllSubCategory();
    public void deleteSubCategory(Long id);
    public SubCategory updateSubCategory(Long id,SubCategoryDto newcat);
    public Accommodation AddAccommodation(String adres, String  local, String rules, float rente, int nbrrooom, MultipartFile multipartFile) throws IOException;

    byte[] getPhotoAccommodation(Long id);

    List<Accommodation> getAllAccommodatiom();

    Accommodation getAccommodation(Long id);

    void deleteAccommodation(Long id);

    Accommodation updateAccommodation(Long id, String adres, String local, String rules, float rente, int nbrrooom, MultipartFile multipartFile) throws IOException;
}
