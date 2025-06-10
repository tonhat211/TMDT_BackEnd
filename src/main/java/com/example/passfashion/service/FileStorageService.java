// package com.example.passfashion.service;

// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
// import java.util.UUID;

// import org.springframework.stereotype.Service;
// import org.springframework.web.multipart.MultipartFile;

// @Service
// public class FileStorageService {
// private final String uploadDir = "uploads/"; // Thư mục lưu ảnh

// public FileStorageService() {
// // Tạo thư mục uploads nếu chưa tồn tại
// File directory = new File(uploadDir);
// if (!directory.exists()) {
// directory.mkdirs();
// }
// }

// public String storeFile(MultipartFile file) throws IOException {
// if (file.isEmpty()) {
// throw new IllegalArgumentException("File không được rỗng");
// }

// // Tạo tên file duy nhất
// String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
// Path filePath = Paths.get(uploadDir + fileName);
// file.transferTo(filePath);
// // Lưu file
// Files.write(filePath, file.getBytes());

// // Trả về URL (giả sử server chạy trên localhost:8080)
// return "/uploads/" + fileName;
// }
// }
