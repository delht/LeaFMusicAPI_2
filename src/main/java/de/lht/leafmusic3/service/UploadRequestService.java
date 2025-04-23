package de.lht.leafmusic3.service;

import de.lht.leafmusic3.entity.RequestStatus;
import de.lht.leafmusic3.entity.UploadRequest;
import de.lht.leafmusic3.entity.UserAccount;
import de.lht.leafmusic3.repository.UploadRequestRepository;
import de.lht.leafmusic3.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor //bo autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadRequestService {

    final UploadRequestRepository requestRepository;
    final UserRepository userRepository;



    public List<UploadRequest> getPendingRequests() {
        return requestRepository.findByStatus(RequestStatus.PENDING);
    }

    public List<UploadRequest> getPendingRequests2() {
        return requestRepository.findAll();
    }

    public UploadRequest getRequestById(long id) {
        return requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu với ID: " + id));
    }



//    ===========================================================================================

    public UploadRequest createRequest(String email, String message) {
        UploadRequest req = new UploadRequest();
        req.setEmail(email);
        req.setMessage(message);
        return requestRepository.save(req);
    }

    public UploadRequest approveRequest(Long id) {
        UploadRequest req = requestRepository.findById(id).orElseThrow();
        req.setStatus(RequestStatus.APPROVED);
        req.setReviewedAt(LocalDateTime.now());

        // Update user
        UserAccount user = userRepository.findByEmail(req.getEmail()).orElseThrow();
        user.setUpload(1);
        userRepository.save(user);

        return requestRepository.save(req);
    }

//    public UploadRequest rejectRequest(Long id) {
//        UploadRequest req = requestRepository.findById(id).orElseThrow();
//        req.setStatus(RequestStatus.REJECTED);
//        req.setReviewedAt(LocalDateTime.now());
//        return requestRepository.save(req);
//    }

    public UploadRequest rejectRequest(Long id) {
        UploadRequest request = requestRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu"));

        if (request.getStatus() == RequestStatus.APPROVED) {
            UserAccount user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy user theo email"));
            user.setUpload(0);
            userRepository.save(user);
        }

        request.setStatus(RequestStatus.REJECTED);
        request.setReviewedAt(LocalDateTime.now());
        return requestRepository.save(request);
    }




}
