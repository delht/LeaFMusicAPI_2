package de.lht.leafmusic3.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UploadRequestDTO {
    private String email;
    private String message;
}
