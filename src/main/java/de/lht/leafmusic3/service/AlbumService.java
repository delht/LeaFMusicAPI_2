package de.lht.leafmusic3.service;

import de.lht.leafmusic3.cloud.GetPubID;
import de.lht.leafmusic3.cloud.repo.DeleteFile;
import de.lht.leafmusic3.cloud.repo.UploadFile;
import de.lht.leafmusic3.dto.album.AlbumDTO;
import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
import de.lht.leafmusic3.dto.request.Album_Request;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.mapper.AlbumMapper;
import de.lht.leafmusic3.repository.AlbumRepository;
import de.lht.leafmusic3.repository.ArtistRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

    @Slf4j
    @Service
    @RequiredArgsConstructor //bo autowired
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;

//    public AlbumService(AlbumRepository albumRepository, AlbumMapper albumMapper) {
//        this.albumRepository = albumRepository;
//        this.albumMapper = albumMapper;
//    }

    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        System.out.println("Dữ liệu album" + albums);
        return albumMapper.toDTOs(albums);
    }

    public List<AlbumDTO> getAlbumsByArtist(int artistId) {
        List<Album> albums = albumRepository.findByIdArtist(artistId);
        return albumMapper.toDTOs(albums);
    }

    public List<AlbumDTO> getRandomAlbums(int limit){
        List<Album> albums = albumRepository.findRandomAlbums(limit);
        return albumMapper.toDTOs(albums);
    }

//    =================================================================================

    private final DeleteFile deleteFile;
    private final UploadFile uploadFile;
    private final GetPubID getPubID;


    @Transactional
    public Album addAlbum(MultipartFile img, AlbumRequestDTO albumRequestDTO) throws IOException {
        try {

            // Upload ảnh
            String folderImg = "LeaFMusic2/Images/Album/";
            String fileUrlImg = uploadFile.uploadFile(img, folderImg);

            // Tạo đối tượng Album
            Album album = new Album();
            album.setName(albumRequestDTO.getName());
            album.setReleaseDate(albumRequestDTO.getReleaseDate());
            album.setIdArtist(albumRequestDTO.getIdArtist());
            album.setImageUrl(fileUrlImg);  // Gán lại URL ảnh đã upload

            System.out.println("Lưu album: " + album.getName() + ", Image URL: " + fileUrlImg);

            return albumRepository.save(album);
        } catch (IOException e) {
            throw new IOException("Lỗi khi xử lý ảnh", e);
        }
    }

    public void deleteAlbum(int id) throws IOException {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay album co id: "+id));

        String folderImg = "LeaFMusic2/Images/Album/";
        String fileUrlImg = album.getImageUrl();
        String publicIdImg = getPubID.layPublicIdTuURL(fileUrlImg, folderImg);

        log.info("Url file cần xóa {}", fileUrlImg);
        deleteFile.deleteFile(publicIdImg);

        albumRepository.delete(album);
        log.info("Album với ID {} đã được xóa thành công.", id);
    }


    @Transactional
    public Album updateAlbum(int id, MultipartFile img, AlbumRequestDTO albumRequestDTO) throws IOException {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy album với id: " + id));

        // Cập nhật thông tin text
        album.setName(albumRequestDTO.getName());
        album.setReleaseDate(albumRequestDTO.getReleaseDate());
        album.setIdArtist(albumRequestDTO.getIdArtist());

        // Nếu có ảnh mới thì xử lý upload và xoá ảnh cũ
        if (img != null && !img.isEmpty()) {
            String folderImg = "LeaFMusic2/Images/Album/";
            String oldImageUrl = album.getImageUrl();

            // Xóa ảnh cũ
            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                String publicIdImg = getPubID.layPublicIdTuURL(oldImageUrl, folderImg);
                deleteFile.deleteFile(publicIdImg);
                log.info("Đã xóa ảnh cũ: {}", publicIdImg);
            }

            // Upload ảnh mới
            String newImageUrl = uploadFile.uploadFile(img, folderImg);
            album.setImageUrl(newImageUrl);
            log.info("Đã upload ảnh mới: {}", newImageUrl);
        } else {
            log.info("Không có ảnh mới, giữ nguyên ảnh cũ.");
        }

        return albumRepository.save(album);
    }












}
