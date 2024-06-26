package net.javaguides.giftbackend.controller;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.service.ProductsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;



import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/imgProducts")
public class ImgProductController {
    private final ProductsService productsService;

    @PostMapping
    public  ResponseEntity<ImgProductDto> createImgProduct(@RequestBody ImgProductDto imgProductDto){
        ImgProductDto saveImgProduct = productsService.createImgProduct(imgProductDto);
        return new ResponseEntity<>(saveImgProduct, HttpStatus.CREATED);
    }

    //getImageByProductID

    @GetMapping("first/{productID}")
    public ResponseEntity<ImgProductDto> getImageByProductID(@PathVariable("productID") Products productID){
        ImgProductDto imgProductDto = productsService.getImageByProductID(productID);
        return  ResponseEntity.ok(imgProductDto);
    }

    //getAllImageByProductID
    @GetMapping("{productID}")
    public ResponseEntity<List<ImgProductDto>> getAllImageByProductID(@PathVariable("productID")Products productID){
        List<ImgProductDto> imgProductDtos = productsService.getAllImageByProductID(productID);
        return  ResponseEntity.ok(imgProductDtos);
    }

    @DeleteMapping("detele/{id}")
    public ResponseEntity<?> deleteImgProduct(@PathVariable("id") Long id){
        productsService.deleteImgProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//   @PostMapping("/upload")
//public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
//    try {
//        // Kiểm tra nếu tệp trống
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is empty");
//        }
//
//        // Lưu tệp ảnh vào thư mục uploads trong nguồn của ứng dụng Spring Boot
//        String uploadsDir = "src/main/resources/static/uploads"; // Thư mục lưu trữ tệp ảnh trên máy chủ
//        String fileName = file.getOriginalFilename(); // Lấy tên gốc của tệp ảnh
//        String filePath = uploadsDir + "/" + fileName;
//        File dest = new File(filePath);
//        file.transferTo(dest);
//
//        // Trả về đường dẫn của tệp ảnh
//        String imagePath = "/api/images/" + fileName;
//        return ResponseEntity.ok().body(imagePath);
//    } catch (IOException e) {
//        e.printStackTrace();
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file: " + e.getMessage());
//    }
//}




}
