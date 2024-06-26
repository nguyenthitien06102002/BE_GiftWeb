package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ProductsDto;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.service.ExcelService;
import net.javaguides.giftbackend.service.ProductsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductsController {
    private final ProductsService productsService;
    private final ExcelService excelService;

    //create product
    @PostMapping
    public ResponseEntity<ProductsDto> createProduct(@RequestBody ProductsDto productsDto){
        ProductsDto savedProduct = productsService.createProduct(productsDto);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @GetMapping
    //getAllProducts
    public ResponseEntity<List<ProductsDto>> getAllProducts(){
        List<ProductsDto> productsDtos =productsService.getAllProducts();
        return  ResponseEntity.ok(productsDtos);
    }

    //getProductByID
    @GetMapping("{id}")
    public ResponseEntity<ProductsDto> getproductById(@PathVariable("id") Long productId){
        ProductsDto productsDto = productsService.getProductByID(productId);
        return ResponseEntity.ok(productsDto);
    }



    @GetMapping("/search")
    public ResponseEntity<List<ProductsDto>> searchProducts(@RequestParam(value = "keyword") String keyword){
        List<ProductsDto> productsDtos = productsService.searchProducts(keyword);
        return ResponseEntity.ok(productsDtos);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<ProductsDto> updateUser(@PathVariable("id") Long productId, @RequestBody ProductsDto updateProduct){
        ProductsDto productsDto = productsService.updateProduct(productId, updateProduct);
        return ResponseEntity.ok(productsDto);
    }

    @PutMapping("deleteProduct/{id}")
    public ResponseEntity<ProductsDto> deleteProduct(@PathVariable("id") Long productId){
        ProductsDto productsDto = productsService.deleteProduct(productId);
        return ResponseEntity.ok(productsDto);
    }

    @PostMapping("/upload")
    public List<Products> uploadProducts(@RequestParam("file") MultipartFile file) {
        return productsService.saveProductsFromExcel(file);
    }


//    @DeleteMapping("detele/{id}")
//    public ResponseEntity<?> deleteImgProduct(@PathVariable("id") Long id){
//        productsService.deleteImgProduct(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
