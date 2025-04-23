package de.lht.leafmusic3.repository;

import de.lht.leafmusic3.entity.RequestStatus;
import de.lht.leafmusic3.entity.UploadRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadRequestRepository extends JpaRepository<UploadRequest, Long> {
    List<UploadRequest> findByStatus(RequestStatus status);
}

