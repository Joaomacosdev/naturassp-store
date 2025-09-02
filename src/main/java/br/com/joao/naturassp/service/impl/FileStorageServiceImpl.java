package br.com.joao.naturassp.service.impl;

import br.com.joao.naturassp.config.FileStorageConfig;
import br.com.joao.naturassp.infra.exception.FileStorageException;
import br.com.joao.naturassp.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Objects;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;
    private static final Logger LOGGER = LoggerFactory.getLogger(FileStorageService.class);

    public FileStorageServiceImpl(FileStorageConfig fileStorageConfig) {
        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            LOGGER.info("Creating Directories");
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            LOGGER.error("Could not create the director where files will be stored");
            throw new FileStorageException("Could not create the director where files will be stored", ex.getCause());
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename Contains a Invalid path Sequence " + fileName);

            }

            LOGGER.info("Save file in Disk");
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        } catch (IOException e) {
            LOGGER.error("Could not store file " + fileName + " . Please try Again!");
            throw new FileStorageException("Could not store file " + fileName + ". Please try Again", e);
        }
    }
}
