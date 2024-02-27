package tn.esprit.coco.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.coco.entity.*;
import tn.esprit.coco.service.MarketPlaceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/MarketPlace")
public class MarketPlaceController {
    @Autowired
    private MarketPlaceService marketPlaceService;

    @PostMapping("/AddCategoryProduct")
    public CategoryProduct createCategoryProduct(@RequestBody CategoryProduct categoryProduct) {
        return marketPlaceService.createCategoryProduct(categoryProduct);
    }

    @GetMapping("/GetCategoryProduct")
    public List<CategoryProduct> getAllCategoryProducts() {
        return marketPlaceService.getAllCategoryProducts();
    }

    @GetMapping("/CategoryProduct/{idCategory}")
    public CategoryProduct getCategoryProductById(@PathVariable Long idCategory) {
        return marketPlaceService.getCategoryProductById(idCategory);
    }

    @PutMapping("/CategoryProduct/{idCategory}")
    public CategoryProduct updateCategoryProduct(@PathVariable Long idCategory, @RequestBody CategoryProduct categoryProduct) {
        return marketPlaceService.updateCategoryProduct(idCategory, categoryProduct);
    }

    @DeleteMapping("/CategoryProduct/{idCategory}")
    public void deleteCategoryProduct(@PathVariable Long idCategory) {
        marketPlaceService.deleteCategoryProduct(idCategory);
    }
    /////subcategory
    @PostMapping("/SubCategory")
    public SubCategoryProduct createSubCategoryProduct(@RequestBody SubCategoryProduct subCategoryProduct) {
        return marketPlaceService.createSubCategoryProduct(subCategoryProduct);
    }

    @GetMapping("/SubCategoryProduct")
    public List<SubCategoryProduct> getAllSubCategoryProducts() {
        return marketPlaceService.getAllSubCategoryProducts();
    }

    @GetMapping("/SubCategoryProduct/{idSubCategory}")
    public SubCategoryProduct getSubCategoryProductById(@PathVariable Long idSubCategory) {
        return marketPlaceService.getSubCategoryProductById(idSubCategory);
    }

    @PutMapping("/SubCategoryProduct/{idSubCategory}")
    public SubCategoryProduct updateSubCategoryProduct(@PathVariable Long idSubCategory, @RequestBody SubCategoryProduct subCategoryProduct) {
        return marketPlaceService.updateSubCategoryProduct(idSubCategory, subCategoryProduct);
    }

    @DeleteMapping("/SubCategoryProduct/{idSubCategory}")
    public void deleteSubCategoryProduct(@PathVariable Long idSubCategory) {
        marketPlaceService.deleteSubCategoryProduct(idSubCategory);
    }
    ///////product
    @PostMapping("/AddProduct")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addedProduct = marketPlaceService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/Product/{idProduct}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long idProduct, @RequestBody Product product) {
        Product updatedProduct = marketPlaceService.updateProduct(idProduct, product);
        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/Product/{idProduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long idProduct) {
        marketPlaceService.deleteProduct(idProduct);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/Product/{idProduct}")
    public ResponseEntity<Product> getProductById(@PathVariable Long idProduct) {
        Product product = marketPlaceService.getProductById(idProduct);
        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/Product")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = marketPlaceService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    ////////////////Picture
    @PostMapping("/Picture")
    public ResponseEntity<PictureProduct> addPictureProduct(@RequestBody PictureProduct pictureProduct) {
        PictureProduct addedPictureProduct = marketPlaceService.addPictureProduct(pictureProduct);
        return new ResponseEntity<>(addedPictureProduct, HttpStatus.CREATED);
    }

    @PutMapping("/Picture/{idpicture}")
    public ResponseEntity<PictureProduct> updatePictureProduct(@PathVariable Long idpicture, @RequestBody PictureProduct pictureProduct) {
        PictureProduct updatedPictureProduct = marketPlaceService.updatePictureProduct(idpicture, pictureProduct);
        if (updatedPictureProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPictureProduct, HttpStatus.OK);
    }

    @DeleteMapping("/Picture/{idpicture}")
    public ResponseEntity<Void> deletePictureProduct(@PathVariable Long idpicture) {
        marketPlaceService.deletePictureProduct(idpicture);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/Picture/{idpicture}")
    public ResponseEntity<PictureProduct> getPictureProductById(@PathVariable Long idpicture) {
        PictureProduct pictureProduct = marketPlaceService.getPictureProductById(idpicture);
        if (pictureProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pictureProduct, HttpStatus.OK);
    }

    @GetMapping("/Picture")
    public ResponseEntity<List<PictureProduct>> getAllPictureProducts() {
        List<PictureProduct> pictureProducts = marketPlaceService.getAllPictureProducts();
        return new ResponseEntity<>(pictureProducts, HttpStatus.OK);
    }
    /////ORDER
    @PostMapping("/Order")
    public ResponseEntity<OrderProduct> addOrderProduct(@RequestBody OrderProduct orderProduct) {
        OrderProduct addedOrderProduct = marketPlaceService.addOrderProduct(orderProduct);
        return new ResponseEntity<>(addedOrderProduct, HttpStatus.CREATED);
    }

    @PutMapping("/Order/{idOrder}")
    public ResponseEntity<OrderProduct> updateOrderProduct(@PathVariable Long idOrder, @RequestBody OrderProduct orderProduct) {
        OrderProduct updatedOrderProduct = marketPlaceService.updateOrderProduct(idOrder, orderProduct);
        if (updatedOrderProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedOrderProduct, HttpStatus.OK);
    }

    @DeleteMapping("/Order/{idOrder}")
    public ResponseEntity<Void> deleteOrderProduct(@PathVariable Long idOrder) {
        marketPlaceService.deleteOrderProduct(idOrder);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/Order/{idOrder}")
    public ResponseEntity<OrderProduct> getOrderProductById(@PathVariable Long idOrder) {
        OrderProduct orderProduct = marketPlaceService.getOrderProductById(idOrder);
        if (orderProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderProduct, HttpStatus.OK);
    }

    @GetMapping("/Order")
    public ResponseEntity<List<OrderProduct>> getAllOrderProducts() {
        List<OrderProduct> orderProducts = marketPlaceService.getAllOrderProducts();
        return new ResponseEntity<>(orderProducts, HttpStatus.OK);
    }
    //////WishList
    @PostMapping("/WishList")
    public ResponseEntity<WishList> addWishList(@RequestBody WishList wishList) {
        WishList addedWishList = marketPlaceService.addWishList(wishList);
        return new ResponseEntity<>(addedWishList, HttpStatus.CREATED);
    }

    @PutMapping("/WishList/{idList}")
    public ResponseEntity<WishList> updateWishList(@PathVariable Long idList, @RequestBody WishList wishList) {
        WishList updatedWishList = marketPlaceService.updateWishList(idList, wishList);
        if (updatedWishList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedWishList, HttpStatus.OK);
    }

    @DeleteMapping("/WishList/{idList}")
    public ResponseEntity<Void> deleteWishList(@PathVariable Long idList) {
        marketPlaceService.deleteWishList(idList);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/WishList/{idList}")
    public ResponseEntity<WishList> getWishListById(@PathVariable Long idList) {
        WishList wishList = marketPlaceService.getWishListById(idList);
        if (wishList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(wishList, HttpStatus.OK);
    }

    @GetMapping("/WishList")
    public ResponseEntity<List<WishList>> getAllWishLists() {
        List<WishList> wishLists = marketPlaceService.getAllWishLists();
        return new ResponseEntity<>(wishLists, HttpStatus.OK);
    }


}
