package br.com.joao.naturassp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {

    private String uploadDir;

    public FileStorageConfig() {
    }

    public FileStorageConfig(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadDir() {
        return uploadDir;
    }

    public FileStorageConfig setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
        return this;
    }
}
