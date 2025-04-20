package de.lht.leafmusic3.service;

import de.lht.leafmusic3.cloud.GetPubID;
import de.lht.leafmusic3.cloud.repo.DeleteFile;
import de.lht.leafmusic3.cloud.repo.UploadFile;
import de.lht.leafmusic3.dto.album.Album2DTO;
import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
import de.lht.leafmusic3.dto.artist.Artist2DTO;
import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.dto.artist.ArtistRequestDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.mapper.ArtistMapper;
import de.lht.leafmusic3.mapper.SongMapper;
import de.lht.leafmusic3.repository.ArtistRepository;
import de.lht.leafmusic3.repository.SongRepository;
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
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;
    private final SongMapper songMapper;
    private final SongRepository songRepository;

    public List<ArtistDTO> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        System.out.println(artists);
        return artistMapper.toDTOs(artists);
    }

    public List<Artist2DTO> getAllArtist2() {
        List<Artist2DTO> artist = artistRepository.findAllArtist();
        System.out.println("Dữ liệu artist: " + artist);
        return artist;
    }



    public ArtistDTO getArtistById(String id) {
        String artistId = String.valueOf(id);
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("ko cos"));
        return artistMapper.toDTO(artist);
    }

    public List<ArtistDTO> getRandomArtists(int limit) {
        List<Artist> artists = artistRepository.findRandomAritsts(limit);
        return artistMapper.toDTOs(artists);
    }


//    ======================================================================

    private final DeleteFile deleteFile;
    private final UploadFile uploadFile;
    private final GetPubID getPubID;

    @Transactional
    public Artist createArtist(MultipartFile img, ArtistRequestDTO artistRequestDTO) throws IOException {
        try{
            String folderImg = "LeaFMusic2/Images/Artist/";
            String fileUrlImg = uploadFile.uploadFile(img, folderImg);

            Artist artist = new Artist();
            artist.setName(artistRequestDTO.getName());
            artist.setImageUrl(fileUrlImg);

            System.out.println("Lưu artist: " + artist.getName() + ", Image URL: " + fileUrlImg);

            return artistRepository.save(artist);
        }catch (IOException e){
            throw new IOException("Lỗi khi xử lý ảnh", e);
        }
    }

    public void deleteArtist(String id) throws IOException {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay artist co id: " + id));

        String folderImg = "LeaFMusic2/Images/Artist/";
        String fileUrlImg = artist.getImageUrl();

        log.info("➡ Bắt đầu xóa artist với ID {}", id);
        log.info("URL ảnh cũ: {}", fileUrlImg);
        log.info("FolderImg cần tìm: {}", folderImg);

        if (fileUrlImg != null && !fileUrlImg.isEmpty()) {
            int folderIndex = fileUrlImg.indexOf(folderImg);
            int dotIndex = fileUrlImg.lastIndexOf(".");
            log.info("Vị trí folder trong URL: {}", folderIndex);
            log.info("Vị trí dấu chấm cuối: {}", dotIndex);

            if (folderIndex != -1 && dotIndex != -1 && folderIndex < dotIndex) {
                String publicIdImg = getPubID.layPublicIdTuURL(fileUrlImg, folderImg);
                deleteFile.deleteFile(publicIdImg);
                log.info("✅ Đã xóa file ảnh với public ID: {}", publicIdImg);
            } else {
                log.warn("⚠ Không thể trích xuất public ID từ URL ảnh: {}. Bỏ qua xóa ảnh.", fileUrlImg);
            }
        } else {
            log.info("Artist không có ảnh để xóa.");
        }

        artistRepository.delete(artist);
        log.info("✅ Artist với ID {} đã được xóa khỏi hệ thống.", id);
    }


    @Transactional
    public Artist updateArtist(String id, MultipartFile img, ArtistRequestDTO artistRequestDTO) throws IOException {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy artist với id: " + id));

        // Cập nhật thông tin text
        artist.setName(artistRequestDTO.getName());

        // Nếu có ảnh mới thì xử lý upload và xoá ảnh cũ
        if (img != null && !img.isEmpty()) {
            String folderImg = "LeaFMusic2/Images/Album/";
            String oldImageUrl = artist.getImageUrl();

            log.info("➡ Bắt đầu xử lý ảnh mới");
            log.info("Old image URL: {}", oldImageUrl);
            log.info("Folder path cần tìm: {}", folderImg);

            // Xóa ảnh cũ
            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                int folderIndex = oldImageUrl.indexOf(folderImg);
                int dotIndex = oldImageUrl.lastIndexOf(".");
                log.info("Vị trí folder trong URL: {}", folderIndex);
                log.info("Vị trí dấu chấm cuối trong URL: {}", dotIndex);

                if (folderIndex != -1 && dotIndex != -1 && folderIndex < dotIndex) {
                    String publicIdImg = getPubID.layPublicIdTuURL(oldImageUrl, folderImg);
                    deleteFile.deleteFile(publicIdImg);
                    log.info("Đã xóa ảnh cũ: {}", publicIdImg);
                } else {
                    log.warn("⚠ Không thể lấy publicId từ URL: {}, bỏ qua xoá ảnh cũ.", oldImageUrl);
                }
            } else {
                log.info("Không có ảnh cũ để xóa.");
            }

            // Upload ảnh mới
            String newImageUrl = uploadFile.uploadFile(img, folderImg);
            artist.setImageUrl(newImageUrl);
            log.info("Đã upload ảnh mới: {}", newImageUrl);
        } else {
            log.info("Không có ảnh mới, giữ nguyên ảnh cũ.");
        }


        return artistRepository.save(artist);
    }




}
