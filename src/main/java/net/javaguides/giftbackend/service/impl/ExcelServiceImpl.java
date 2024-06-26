package net.javaguides.giftbackend.service.impl;

import lombok.AllArgsConstructor;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.repository.CategoryRepository;
import net.javaguides.giftbackend.repository.ProductTopicRepository;
import net.javaguides.giftbackend.repository.ProductsRepository;
import net.javaguides.giftbackend.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service
@AllArgsConstructor
public class ExcelServiceImpl implements ExcelService {
    private ProductsRepository productRepository;
    private CategoryRepository categoryRepository;

    private ProductTopicRepository productTopicRepository;

        @Override
        public void importProducts(MultipartFile file) throws IOException {
            List<Products> products = new ArrayList<>();
            try (InputStream inputStream = file.getInputStream();
                 Workbook workbook = new XSSFWorkbook(inputStream)) {

                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rows = sheet.iterator();

                while (rows.hasNext()) {
                    Row row = rows.next();
                    if (row.getRowNum() == 0) {
                        continue; // Skip header row
                    }

                    Products product = new Products();
                    product.setProductName(row.getCell(0).getStringCellValue());
                    product.setDetail(row.getCell(1).getStringCellValue());
                    product.setPrice(row.getCell(2).getNumericCellValue());
                    product.setSalePrice( row.getCell(3).getNumericCellValue());
                    product.setStock((long) row.getCell(4).getNumericCellValue());

                    Long categoryId = (long) row.getCell(5).getNumericCellValue();
                    product.setCategoryId(categoryRepository.findById(categoryId).orElse(null));

                    Long themeId = (long) row.getCell(6).getNumericCellValue();
//                    product.setProductTopics(productTopicRepository.findById(themeId).orElse(null));
                    product.setActive(row.getCell(7).getBooleanCellValue());
                    product.setStatus(row.getCell(9).getBooleanCellValue());

                    products.add(product);
                }
            }

            productRepository.saveAll(products);
}



}
