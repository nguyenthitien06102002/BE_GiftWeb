package net.javaguides.giftbackend.service;


import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.ImgProduct;
import net.javaguides.giftbackend.entity.PasswordResetToken;
import net.javaguides.giftbackend.entity.Products;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface ProductsService {

    //tao product
    ProductsDto createProduct(ProductsDto productsDto);
    List<ProductsDto> getAllProducts();

    ProductsDto getProductByID(Long productID);
//createImgProduct
    ImgProductDto createImgProduct(ImgProductDto imgProductDto);

    //getImageByProductID
    ImgProductDto getImageByProductID(Products productID);

    //getAllImageByProductID

    List<ImgProductDto> getAllImageByProductID(Products productID);

    //createGroupProduct



    //tim kiem

    List<ProductsDto> searchProducts(String keyword);


    void deleteImgProduct(Long id) ;

    ProductsDto updateProduct(long productId, ProductsDto updateProduct);
    ProductsDto deleteProduct(Long productId);

    List<Products> saveProductsFromExcel(MultipartFile file);




}
