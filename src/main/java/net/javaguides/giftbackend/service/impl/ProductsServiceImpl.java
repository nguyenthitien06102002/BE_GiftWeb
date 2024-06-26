package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;

import net.javaguides.giftbackend.dto.ImgProductDto;
import net.javaguides.giftbackend.dto.ProductsDto;

import net.javaguides.giftbackend.dto.UsersDto;
import net.javaguides.giftbackend.entity.*;
import net.javaguides.giftbackend.exception.ResourceNotFoundException;

import net.javaguides.giftbackend.mapper.ImgProductMapper;
import net.javaguides.giftbackend.mapper.ProductsMapper;
import net.javaguides.giftbackend.mapper.UsersMapper;
import net.javaguides.giftbackend.repository.*;
import net.javaguides.giftbackend.service.ProductsService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductsServiceImpl implements ProductsService {
    private ProductsRepository productsRepository;
    private ImgProductRepository imgProductsRepository;
    private CategoryRepository categoryRepository;
    private ProductTopicRepository productTopicRepository;
    private ProductTopicsRepository productTopicsRepository;




    @Override
    public ProductsDto createProduct(ProductsDto productsDto) {
        // Lấy thông tin từ DTO
        Long categoryId = productsDto.getCategoryId().getId();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return null;
        }
        Products products = ProductsMapper.mapToProduct(productsDto);
        products.setCategoryId(category);
        // Lưu sản phẩm vào cơ sở dữ liệu
        Products saveProducts = productsRepository.save(products);
        return ProductsMapper.maptoProductsDto(saveProducts);
    }




@Override
public List<ProductsDto> getAllProducts() {
    List<Products> allProducts = productsRepository.findAll();
    List<Products> activeProducts = allProducts.stream()
            .filter(product -> product.getStatus())
            .collect(Collectors.toList());
    return activeProducts.stream()
            .map(product -> ProductsMapper.maptoProductsDto(product))
            .collect(Collectors.toList());
}
//getProductByID
    @Override
    public ProductsDto getProductByID(Long productID) {
        Products products =  productsRepository.findById(productID)
                .orElseThrow(() ->
                        new ResourceNotFoundException("user is not exist with given id: " + productID));

        return ProductsMapper.maptoProductsDto(products);
    }

    @Override
    public ImgProductDto createImgProduct(ImgProductDto imgProductDto) {
        Long productId = imgProductDto.getProductID().getId();
        Products product = productsRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        ImgProduct imgProduct = ImgProductMapper.mapToImgProduct(imgProductDto);
        imgProduct.setProductID(product); // Set the product to the image
        ImgProduct saveImgProduct = imgProductsRepository.save(imgProduct);
        return ImgProductMapper.mapToImgProductDto(saveImgProduct);
    }


//findFirstByProductID
    @Override
    public ImgProductDto getImageByProductID(Products productID) {

        ImgProduct imgProduct = imgProductsRepository.findFirstByProductID(productID);
        if (imgProduct == null) {
           return null;
        }
        return ImgProductMapper.mapToImgProductDto(imgProduct);
    }


//getAllImageByProductID
    @Override
    public List<ImgProductDto> getAllImageByProductID(Products productID) {
        List<ImgProduct> imgProducts = imgProductsRepository.findByProductID(productID);
        if (imgProducts.isEmpty()) {
            throw new ResourceNotFoundException("No images found for product with ID: " + productID.getId());
        }
        return imgProducts.stream()
                .map(ImgProductMapper::mapToImgProductDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductsDto> searchProducts(String keyword) {
        List<Products> productsSearch = productsRepository.findByKeyword(keyword);
        return productsSearch.stream().map((productSeach) -> ProductsMapper.maptoProductsDto(productSeach))
                .filter(product -> product.getStatus())
                .collect(Collectors.toList());
    }

    @Override
    public void deleteImgProduct(Long id) {
        ImgProduct imgProduct = imgProductsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Image is not exist with given id: " + id));
        imgProductsRepository.delete(imgProduct);

    }

    @Override
    public ProductsDto updateProduct(long productId, ProductsDto updateProduct) {

        Products products =  productsRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + productId));
        Long categoryId = updateProduct.getCategoryId().getId();
        Category category = categoryRepository.findById(categoryId).orElse(null);
        Long themeId = updateProduct.getThemeId().getId();
        ProductTopic theme = productTopicRepository.findById(themeId).orElse(null);
        products.setProductName(updateProduct.getProductName());
        products.setDetail(updateProduct.getDetail());
        products.setPrice(updateProduct.getPrice());
        products.setSalePrice(updateProduct.getSalePrice());
        products.setStock(updateProduct.getStock());
        products.setCategoryId(category);
        products.setThemeId(theme);
        products.setActive(updateProduct.getActive());
        Products updateProductObj = productsRepository.save(products);
        return ProductsMapper.maptoProductsDto(updateProductObj);
    }



    @Override
    public ProductsDto deleteProduct(Long productId) {
        Products products = productsRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found with id: " + productId));
        products.setStatus(false);
        Products updateStatus = productsRepository.save(products);
        return ProductsMapper.maptoProductsDto(updateStatus);

    }

    @Override
    public List<Products> saveProductsFromExcel(MultipartFile file) {
        List<Products> productsList = new ArrayList<>();
        try {
            InputStream is = file.getInputStream();
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // Skip header row
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Products product = new Products();
                product.setProductName(getStringCellValue(currentRow.getCell(0)));
                product.setDetail(getStringCellValue(currentRow.getCell(1)));
                product.setPrice(getNumericCellValue(currentRow.getCell(2)));
                product.setSalePrice(getNumericCellValue(currentRow.getCell(3)));
                product.setStock((long) getNumericCellValue(currentRow.getCell(4)));

                long categoryId = (long) getNumericCellValue(currentRow.getCell(5));
                long themeId = (long) getNumericCellValue(currentRow.getCell(6));

                Optional<Category> category = categoryRepository.findById(categoryId);
                Optional<ProductTopic> theme = productTopicRepository.findById(themeId);

                if (category.isPresent()) {
                    product.setCategoryId(category.get());
                } else {
                    System.out.println("Category not found for ID: " + categoryId);
                }

                if (theme.isPresent()) {
                    product.setThemeId(theme.get());
                } else {
                    System.out.println("Theme not found for ID: " + themeId);
                }

                product.setCreateDate(new Timestamp(System.currentTimeMillis()));
                product.setActive(getBooleanCellValue(currentRow.getCell(7)));
                product.setStatus(getBooleanCellValue(currentRow.getCell(8)));

                productsList.add(product);

                // Log thông tin sản phẩm để kiểm tra
                System.out.println("Added product: " + product);
            }
            workbook.close();

            // Lưu toàn bộ danh sách sản phẩm vào cơ sở dữ liệu
            productsRepository.saveAll(productsList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productsList;
    }





    private String getStringCellValue(Cell cell) {
        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : String.valueOf(cell.getNumericCellValue());
    }

    private double getNumericCellValue(Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            try {
                return Double.parseDouble(cell.getStringCellValue());
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Cannot get numeric value from string cell: " + cell.getStringCellValue());
            }
        } else {
            throw new IllegalStateException("Unexpected cell type: " + cell.getCellType());
        }
    }

    private boolean getBooleanCellValue(Cell cell) {
        if (cell.getCellType() == CellType.BOOLEAN) {
            return cell.getBooleanCellValue();
        } else if (cell.getCellType() == CellType.STRING) {
            return Boolean.parseBoolean(cell.getStringCellValue());
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return cell.getNumericCellValue() != 0;
        } else {
            throw new IllegalStateException("Unexpected cell type: " + cell.getCellType());
        }
    }
}
