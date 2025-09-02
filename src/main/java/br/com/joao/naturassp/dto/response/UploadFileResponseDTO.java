package br.com.joao.naturassp.dto.response;

public record UploadFileResponseDTO(
        String fileName,
        String fileDownloadUri,
        String fileType,
        long size
) {
}
