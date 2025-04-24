package de.lht.leafmusic3.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "upload_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequest;

    private String email;

    private String message;

    @Enumerated(EnumType.STRING)
    private RequestStatus status = RequestStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime reviewedAt;

    @Column(name = "file_url")
    private String fileUrl;


}
