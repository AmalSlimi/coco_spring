package tn.esprit.coco.serviceImp;

import tn.esprit.coco.entity.*;

import java.util.List;

public interface MarketPlaceImpl {
    /////////CATEGORY
    CategoryProduct createCategoryProduct(CategoryProduct categoryProduct);
    List<CategoryProduct> getAllCategoryProducts();
    CategoryProduct getCategoryProductById(Long idCategory);
    CategoryProduct updateCategoryProduct(Long idCategory, CategoryProduct categoryProduct);
    void deleteCategoryProduct(Long idCategory);
    /////////////////SUBCATEGORY
    SubCategoryProduct createSubCategoryProduct(SubCategoryProduct subCategoryProduct);
    List<SubCategoryProduct> getAllSubCategoryProducts();
    SubCategoryProduct getSubCategoryProductById(Long idSubCategory);
    SubCategoryProduct updateSubCategoryProduct(Long idSubCategory, SubCategoryProduct subCategoryProduct);
    void deleteSubCategoryProduct(Long idSubCategory);
    ///////////PICTURE
    PictureProduct addPictureProduct(PictureProduct pictureProduct);
    PictureProduct updatePictureProduct(Long idpicture, PictureProduct pictureProduct);
    void deletePictureProduct(Long idpicture);
    List<PictureProduct> getAllPictureProducts();
    PictureProduct getPictureProductById(Long idpicture);
    /////////////PRODUCt
    Product addProduct(Product product);
    Product updateProduct(Long idProduct, Product product);
    void deleteProduct(Long idProduct);
    List<Product> getAllProducts();
    Product getProductById(Long idProduct);
    /////////ORDER
    OrderProduct addOrderProduct(OrderProduct orderProduct);
    OrderProduct updateOrderProduct(Long idOrder, OrderProduct orderProduct);
    void deleteOrderProduct(Long idOrder);
    List<OrderProduct> getAllOrderProducts();
    OrderProduct getOrderProductById(Long idOrder);
    //////WISHLIST
    WishList addWishList(WishList wishList);
    WishList updateWishList(Long idOrder, WishList wishList);
    void deleteWishList(Long idOrder);
    List<WishList> getAllWishLists();
    WishList getWishListById(Long idOrder);



}
