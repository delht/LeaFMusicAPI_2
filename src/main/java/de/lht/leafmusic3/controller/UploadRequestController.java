package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.upload.UploadRequestDTO;
import de.lht.leafmusic3.entity.UploadRequest;
import de.lht.leafmusic3.service.UploadRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/upload-requests")
public class UploadRequestController {

    private final UploadRequestService requestService;

    @GetMapping("/pending")
    public List<UploadRequest> getPendingRequests() {
        return requestService.getPendingRequests();
    }

    @GetMapping("/all")
    public List<UploadRequest> getAllRequests() {
        return requestService.getPendingRequests2();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UploadRequest> getRequestById(@PathVariable long id) {
        UploadRequest request = requestService.getRequestById(id);
        return ResponseEntity.ok(request);
    }

//    ============================================================================================

    @PostMapping
    public ResponseEntity<?> requestUpload(@RequestBody UploadRequestDTO dto) {
        UploadRequest req = requestService.createRequest(dto.getEmail(), dto.getMessage());
        return ResponseEntity.ok(req);
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.approveRequest(id));
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.rejectRequest(id));
    }
}

