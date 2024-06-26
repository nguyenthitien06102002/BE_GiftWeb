package net.javaguides.giftbackend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {
    void importProducts(MultipartFile file) throws IOException;
}
