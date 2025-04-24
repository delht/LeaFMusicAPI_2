package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.upload.UploadRequestDTO;
import de.lht.leafmusic3.entity.UploadRequest;
import de.lht.leafmusic3.service.UploadRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

//    @PostMapping
//    public ResponseEntity<?> requestUpload(@RequestBody UploadRequestDTO dto) {
//        UploadRequest req = requestService.createRequest(dto.getEmail(), dto.getMessage());
//        return ResponseEntity.ok(req);
//    }
@PostMapping("/new")
public ResponseEntity<?> requestUpload(@RequestParam("email") String email,
                                       @RequestParam("message") String message,
                                       @RequestParam(value = "img", required = false) MultipartFile img) {
    try {
        UploadRequest req = requestService.createRequest(email, message, img);
        return ResponseEntity.ok(req);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi khi xử lý ảnh");
    }
}



    @PostMapping("/approve/{id}/{idArtist}")
    public ResponseEntity<?> approve(@PathVariable Long id, @PathVariable int idArtist) {
        return ResponseEntity.ok(requestService.approveRequest(id, idArtist));
    }

    @PostMapping("/reject/{id}")
    public ResponseEntity<?> reject(@PathVariable Long id) {
        return ResponseEntity.ok(requestService.rejectRequest(id));
    }
}

