package br.com.joao.naturassp.service;


import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    public String storeFile(MultipartFile arquivo);
}
