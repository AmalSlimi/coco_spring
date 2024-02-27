package tn.esprit.coco.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.coco.entity.*;
import tn.esprit.coco.repository.*;
import tn.esprit.coco.serviceImp.MarketPlaceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MarketPlaceService implements MarketPlaceImpl {

    @Autowired
    private CategoryProductRepository categoryProductRepository;
    @Autowired
    private SubCategoryProductRepository subCategoryProductRepository;
    @Autowired
    private PictureProductRepository pictureProductRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderProductRepository orderProductRepository;
    /*@Autowired
    private PaymentProductRepository paymentProductRepository;*/
    @Autowired
    private WishListRepository wishListRepository;
    @Autowired
    private UserService userService;


    @Override
    public CategoryProduct createCategoryProduct(CategoryProduct categoryProduct) {
        return categoryProductRepository.save(categoryProduct);
    }

    @Override
    public List<CategoryProduct> getAllCategoryProducts() {
        return categoryProductRepository.findAll();
    }

    @Override
    public CategoryProduct getCategoryProductById(Long idCategory) {
        return categoryProductRepository.findById(idCategory).orElse(null);
    }

    @Override
    /*public CategoryProduct updateCategoryProduct(Long idCategory, CategoryProduct categoryProduct) {
        CategoryProduct existingCategoryProduct = getCategoryProductById(idCategory);
        existingCategoryProduct.setCategoryName(categoryProduct.getCategoryName());
        existingCategoryProduct.setSubCategorys(categoryProduct.getSubCategorys());
        return categoryProductRepository.save(existingCategoryProduct);
    }*/
   public CategoryProduct updateCategoryProduct(Long categoryId, CategoryProduct updatedCategoryProduct) {

       CategoryProduct existingCategoryProduct = categoryProductRepository.findById(categoryId)
               .orElseThrow(() -> new RuntimeException("CategoryProduct not found with id: " + categoryId)); //Find the existing category by its ID


       existingCategoryProduct.setCategoryName(updatedCategoryProduct.getCategoryName());//new values

       return categoryProductRepository.save(existingCategoryProduct);
   }

    @Override
    public void deleteCategoryProduct(Long idCategory) {
        categoryProductRepository.deleteById(idCategory);
    }
    //////////////////////////////////////SUBCATEGORY
    @Override
    public SubCategoryProduct createSubCategoryProduct(SubCategoryProduct subCategoryProduct) {
        return subCategoryProductRepository.save(subCategoryProduct);
    }

    @Override
    public List<SubCategoryProduct> getAllSubCategoryProducts() {
        return subCategoryProductRepository.findAll();
    }

    @Override
    public SubCategoryProduct getSubCategoryProductById(Long idSubCategory) {
        return subCategoryProductRepository.findById(idSubCategory).orElse(null);
    }

    @Override
   /* public SubCategoryProduct updateSubCategoryProduct(Long idSubCategory, SubCategoryProduct subCategoryProduct) {
        SubCategoryProduct existingSubCategoryProduct = getSubCategoryProductById(idSubCategory);
        existingSubCategoryProduct.setSubCategoryName(subCategoryProduct.getSubCategoryName());
        existingSubCategoryProduct.setCategory(subCategoryProduct.getCategory());
        existingSubCategoryProduct.setProducts(subCategoryProduct.getProducts());
        return subCategoryProductRepository.save(existingSubCategoryProduct);
    }*/
    /*public SubCategoryProduct updateSubCategoryProduct(Long subCategoryId, SubCategoryProduct updatedSubCategoryProduct) {
        SubCategoryProduct existingSubCategoryProduct = subCategoryProductRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("SubCategoryProduct not found with id: " + subCategoryId));

        existingSubCategoryProduct.setSubCategoryName(updatedSubCategoryProduct.getSubCategoryName());
        return subCategoryProductRepository.save(existingSubCategoryProduct);
    }*/
    public SubCategoryProduct updateSubCategoryProduct(Long subCategoryId, SubCategoryProduct updatedSubCategoryProduct) {
        SubCategoryProduct existingSubCategoryProduct = subCategoryProductRepository.findById(subCategoryId)
                .orElseThrow(() -> new RuntimeException("SubCategoryProduct not found with id: " + subCategoryId));

        if (updatedSubCategoryProduct.getSubCategoryName() != null) {
            existingSubCategoryProduct.setSubCategoryName(updatedSubCategoryProduct.getSubCategoryName());
        }

        return subCategoryProductRepository.save(existingSubCategoryProduct);
    }

    @Override
    public void deleteSubCategoryProduct(Long idSubCategory) {
        subCategoryProductRepository.deleteById(idSubCategory);
    }
    //////////////////////////////PRODUCT
    @Override
    @Transactional
  /*  public Product addProduct(Product product) {
        SubCategoryProduct subCategoryProduct = getSubCategoryProductById(product.getSubcategory().getIdSubCategory());
        product.setSubcategory(subCategoryProduct);
        User user = userService.getUserById(product.getUser().getId());
        product.setUser(User.builder().build());
        List<WishList> wishlists = product.getWishlists();
        for (WishList wishList : wishlists) {
            wishList.getProducts().add(product);
            updateWishList(wishList.getIdList(), wishList);
        }
        List<PictureProduct> pictureProducts = product.getPictureProducts();
        for (PictureProduct pictureProduct : pictureProducts) {
            pictureProduct.setProduct(product);
            addPictureProduct(pictureProduct);
        }
        OrderProduct orderProduct = getOrderProductById(product.getOrderproduct().getIdOrder());
        product.setOrderproduct(orderProduct);
        return productRepository.save(product);
    }*/

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    @Transactional
   /* public Product updateProduct(Long idProduct, Product product) {
        Product existingProduct = getProductById(idProduct);
        existingProduct.setName(product.getName());
        existingProduct.setTypeProduct(product.getTypeProduct());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setWeight(product.getWeight());
        existingProduct.setPrice(product.getPrice());
        SubCategoryProduct subCategoryProduct = getSubCategoryProductById(product.getSubcategory().getIdSubCategory());
        existingProduct.setSubcategory(subCategoryProduct);

        User user = userService.getUserById(product.getUser().getId());

        existingProduct.setUser(User.builder().build());// USER
        existingProduct.setWishlists(product.getWishlists());
        existingProduct.setOrderproduct(product.getOrderproduct());
        return productRepository.save(existingProduct);
    }*/
   /* public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setTypeProduct(updatedProduct.getTypeProduct());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setQuantity(updatedProduct.getQuantity());
        existingProduct.setWeight(updatedProduct.getWeight());
        existingProduct.setPrice(updatedProduct.getPrice());
        return productRepository.save(existingProduct);

    }*/
    public Product updateProduct(Long productId, Product updatedProduct) {
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        // Update name if not null in the updated product
        if (updatedProduct.getName() != null) {
            existingProduct.setName(updatedProduct.getName());
        }

        // Update typeProduct if not null in the updated product
        if (updatedProduct.getTypeProduct() != null) {
            existingProduct.setTypeProduct(updatedProduct.getTypeProduct());
        }

        // Update description if not null in the updated product
        if (updatedProduct.getDescription() != null) {
            existingProduct.setDescription(updatedProduct.getDescription());
        }

        // Update quantity if not null in the updated product
        if (updatedProduct.getQuantity() != 0) {
            existingProduct.setQuantity(updatedProduct.getQuantity());
        }

        // Update weight if not null in the updated product
        if (updatedProduct.getWeight() != null) {
            existingProduct.setWeight(updatedProduct.getWeight());
        }

        // Update price if not null in the updated product
        if (updatedProduct.getPrice() != 0) {
            existingProduct.setPrice(updatedProduct.getPrice());
        }

        return productRepository.save(existingProduct);
    }

    @Override
    public void deleteProduct(Long idProduct) {
        Product product = getProductById(idProduct);
        List<PictureProduct> pictureProducts = product.getPictureProducts();
        for (PictureProduct pictureProduct : pictureProducts) {
            deletePictureProduct(pictureProduct.getIdpicture());
        }
        productRepository.deleteById(idProduct);
    }
    @Override
    public Product getProductById(Long idProduct) {
        return productRepository.findById(idProduct).orElseThrow(() -> new RuntimeException("Product not found with id " + idProduct));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }



    ////////////////////PICTURE
    @Override
    public PictureProduct addPictureProduct(PictureProduct pictureProduct) {
        /*Product product = getProductById(pictureProduct.getProduct().getIdProduct());
        pictureProduct.setProduct(product);*/
        return pictureProductRepository.save(pictureProduct);
    }

    @Override
   /* public PictureProduct updatePictureProduct(Long idpicture, PictureProduct pictureProduct) {
        PictureProduct existingPictureProduct = getPictureProductById(idpicture);
        existingPictureProduct.setDateAdded(pictureProduct.getDateAdded());
        existingPictureProduct.setFormat(pictureProduct.getFormat());
        existingPictureProduct.setPath(pictureProduct.getPath());
        Product product = getProductById(pictureProduct.getProduct().getIdProduct());
        existingPictureProduct.setProduct(product);
        return pictureProductRepository.save(existingPictureProduct);
    }*/
   /*public PictureProduct updatePictureProduct(Long pictureId, PictureProduct updatedPictureProduct) {
       PictureProduct existingPictureProduct = pictureProductRepository.findById(pictureId)
               .orElseThrow(() -> new RuntimeException("Picture product not found with id: " + pictureId));

       existingPictureProduct.setDateAdded(updatedPictureProduct.getDateAdded());
       existingPictureProduct.setFormat(updatedPictureProduct.getFormat());
       existingPictureProduct.setPath(updatedPictureProduct.getPath());
       return pictureProductRepository.save(existingPictureProduct);
   }*/
    public PictureProduct updatePictureProduct(Long pictureId, PictureProduct updatedPictureProduct) {
        PictureProduct existingPictureProduct = pictureProductRepository.findById(pictureId)
                .orElseThrow(() -> new RuntimeException("Picture product not found with id: " + pictureId));

        // Update attributes if not null in the updatedPictureProduct
        if (updatedPictureProduct.getDateAdded() != null) {
            existingPictureProduct.setDateAdded(updatedPictureProduct.getDateAdded());
        }
        if (updatedPictureProduct.getFormat() != null) {
            existingPictureProduct.setFormat(updatedPictureProduct.getFormat());
        }
        if (updatedPictureProduct.getPath() != null) {
            existingPictureProduct.setPath(updatedPictureProduct.getPath());
        }

        return pictureProductRepository.save(existingPictureProduct);
    }


    @Override
    public void deletePictureProduct(Long idpicture) {
        PictureProduct pictureProduct = getPictureProductById(idpicture);
        Path path = Paths.get(pictureProduct.getPath());
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        pictureProductRepository.deleteById(idpicture);
    }

    @Override
    public List<PictureProduct> getAllPictureProducts() {
        return pictureProductRepository.findAll();
    }

    @Override
    public PictureProduct getPictureProductById(Long idpicture) {
        return pictureProductRepository.findById(idpicture).orElse(null);
    }
    //////////////////////ORDER
    /*@Override
    @Transactional
    public OrderProduct addOrderProduct(OrderProduct orderProduct) {
       PaymentProduct paymentProduct = paymentProductService.getPaymentProductById(orderProduct.getPaymentproduct().getIdPayment());
        orderProduct.setPaymentproduct(paymentProduct); // paymenent
        List<Product> products = orderProduct.getProducts();
        for (Product product : products) {
            product.setOrderproduct(orderProduct);
            updateProduct(product.getIdProduct(), product);
        }
        return orderProductRepository.save(orderProduct);
    }*/
    @Override
    public OrderProduct addOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }

    @Override
    @Transactional
    /*public OrderProduct updateOrderProduct(Long idOrder, OrderProduct orderProduct) {
        OrderProduct existingOrderProduct = getOrderProductById(idOrder);
        existingOrderProduct.setQuantity(orderProduct.getQuantity());
        existingOrderProduct.setAmount(orderProduct.getAmount());
        existingOrderProduct.setAddress(orderProduct.getAddress());
        existingOrderProduct.setPostcode(orderProduct.getPostcode());
        existingOrderProduct.setCity(orderProduct.getCity());
        existingOrderProduct.setNotes(orderProduct.getNotes());
        PaymentProduct paymentProduct = paymentProductService.getPaymentProductById(orderProduct.getPaymentproduct().getIdPayment());
        existingOrderProduct.setPaymentproduct(paymentProduct);
        List<Product> products = orderProduct.getProducts();
        for (Product product : products) {
            product.setOrderproduct(existingOrderProduct);
            updateProduct(product.getIdProduct(), product);
        }
        return orderProductRepository.save(existingOrderProduct);
    }*/
  /* public OrderProduct updateOrderProduct(Long orderId, OrderProduct updatedOrderProduct) {

       OrderProduct existingOrderProduct = orderProductRepository.findById(orderId)
               .orElseThrow(() -> new RuntimeException("Order product not found with id: " + orderId));

       existingOrderProduct.setQuantity(updatedOrderProduct.getQuantity());
       existingOrderProduct.setAmount(updatedOrderProduct.getAmount());
       existingOrderProduct.setAddress(updatedOrderProduct.getAddress());
       existingOrderProduct.setPostcode(updatedOrderProduct.getPostcode());
       existingOrderProduct.setCity(updatedOrderProduct.getCity());
       existingOrderProduct.setNotes(updatedOrderProduct.getNotes());

       return orderProductRepository.save(existingOrderProduct);
   }*/
    public OrderProduct updateOrderProduct(Long orderId, OrderProduct updatedOrderProduct) {
        OrderProduct existingOrderProduct = orderProductRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order product not found with id: " + orderId));

        // Update attributes if not null in the updatedOrderProduct
        if (updatedOrderProduct.getQuantity() != 0) {
            existingOrderProduct.setQuantity(updatedOrderProduct.getQuantity());
        }
        if (updatedOrderProduct.getAmount() != 0) {
            existingOrderProduct.setAmount(updatedOrderProduct.getAmount());
        }
        if (updatedOrderProduct.getAddress() != null) {
            existingOrderProduct.setAddress(updatedOrderProduct.getAddress());
        }
        if (updatedOrderProduct.getPostcode() != 0) {
            existingOrderProduct.setPostcode(updatedOrderProduct.getPostcode());
        }
        if (updatedOrderProduct.getCity() != null) {
            existingOrderProduct.setCity(updatedOrderProduct.getCity());
        }
        if (updatedOrderProduct.getNotes() != null) {
            existingOrderProduct.setNotes(updatedOrderProduct.getNotes());
        }

        return orderProductRepository.save(existingOrderProduct);
    }


    @Override
    @Transactional
    public void deleteOrderProduct(Long idOrder) {
        OrderProduct orderProduct = getOrderProductById(idOrder);
        List<Product> products = orderProduct.getProducts();
        for (Product product : products) {
            product.setOrderproduct(null);
            updateProduct(product.getIdProduct(), product);
        }
        orderProductRepository.deleteById(idOrder);
    }

    @Override
    public List<OrderProduct> getAllOrderProducts() {
        return orderProductRepository.findAll();
    }

    @Override
    public OrderProduct getOrderProductById(Long idOrder) {
        return orderProductRepository.findById(idOrder).orElseThrow(() -> new RuntimeException("OrderProduct not found with id " + idOrder));
    }
    //////////////////WISHLIST
    @Override
    @Transactional
    public WishList addWishList(WishList wishList) {
        /*User user = getUserById(wishList.getUser().getIdUser());
        wishList.setUser(user); ///////USSSEERR
        List<Product> products = wishList.getProducts();
        for (Product product : products) {
            product.getWishlists().add(wishList);
            updateProduct(product.getIdProduct(), product);
        }*/
        return wishListRepository.save(wishList);
    }

    @Override
    @Transactional
   /* public WishList updateWishList(Long idList, WishList wishList) {
        WishList existingWishList = getWishListById(idList);
        existingWishList.setProducts(wishList.getProducts());
        User user = userService.getUserById(wishList.getUser().getIdUser());
        existingWishList.setUser(user);
        List<Product> products = wishList.getProducts();
        for (Product product : products) {
            product.getWishlists().add(existingWishList);
            updateProduct(product.getIdProduct(), product);
        }
        return wishListRepository.save(existingWishList);
    }*/
   /* public WishList updateWishList(Long wishListId, WishList updatedWishList) {

        WishList existingWishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("Wish list not found with id: " + wishListId));


        existingWishList.setWishname(updatedWishList.getWishname());
        existingWishList.setProducts(updatedWishList.getProducts());

        return wishListRepository.save(existingWishList);
    }*/
    public WishList updateWishList(Long wishListId, WishList updatedWishList) {
        WishList existingWishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new RuntimeException("Wish list not found with id: " + wishListId));

        // Update attributes if not null in the updatedWishList
        if (updatedWishList.getWishname() != null) {
            existingWishList.setWishname(updatedWishList.getWishname());
        }
        if (updatedWishList.getProducts() != null) {
            existingWishList.setProducts(updatedWishList.getProducts());
        }

        return wishListRepository.save(existingWishList);
    }

    @Override
    @Transactional
    public void deleteWishList(Long idList) {
        WishList wishList = getWishListById(idList);
        List<Product> products = wishList.getProducts();
        for (Product product : products) {
            product.getWishlists().remove(wishList);
            updateProduct(product.getIdProduct(), product);
        }
        wishListRepository.deleteById(idList);
    }

    @Override
    public List<WishList> getAllWishLists() {
        return wishListRepository.findAll();
    }

    @Override
    public WishList getWishListById(Long idList) {
        return wishListRepository.findById(idList).orElseThrow(() -> new RuntimeException("WishList not found with id " + idList));
    }





}
