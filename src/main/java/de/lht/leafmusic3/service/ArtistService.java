package de.lht.leafmusic3.service;

import de.lht.leafmusic3.exception.AppException;
import de.lht.leafmusic3.dto.artist.Artist2DTO;
import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.dto.artist.ArtistRequestDTO;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.mapper.ArtistMapper;
import de.lht.leafmusic3.mapper.SongMapper;
import de.lht.leafmusic3.repository.ArtistRepository;
import de.lht.leafmusic3.repository.SongRepository;
import de.lht.leafmusic3.storage.constant.StorageFolder;
import de.lht.leafmusic3.storage.service.StorageService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArtistService {
    private final ArtistRepository artistRepository;
    private final ArtistMapper artistMapper;

    public List<ArtistDTO> getAllArtists() {
        List<Artist> artists = artistRepository.findAll();
        return artistMapper.toDTOs(artists);
    }

    public List<Artist2DTO> getAllArtist2() {
        List<Artist2DTO> artist = artistRepository.findAllArtist();
        return artist;
    }

    public ArtistDTO getArtistById(String id) {
        String artistId = String.valueOf(id);
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new AppException(HttpStatus.CONFLICT, "Không tìm thấy tác giả"));
        return artistMapper.toDTO(artist);
    }

    //TODO sửa random
    public List<ArtistDTO> getRandomArtists(int limit) {
        List<Artist> artists = artistRepository.findRandomAritsts(limit);
        return artistMapper.toDTOs(artists);
    }


//    ======================================================================

    private final StorageService storageService;

    @Transactional
    public Artist addArtist(MultipartFile img, ArtistRequestDTO artistRequestDTO) {
        try{

            String fileUrlImg = storageService.upload(img, StorageFolder.ARTIST);

            Artist artist = new Artist();
            artist.setName(artistRequestDTO.getName());
            artist.setImageUrl(fileUrlImg);

            System.out.println("Lưu artist: " + artist.getName() + ", Image URL: " + fileUrlImg);

            return artistRepository.save(artist);
        }catch (IOException e){
            throw new AppException(
              HttpStatus.INTERNAL_SERVER_ERROR,
              "Upload ảnh thất bại."
            );
        }
    }

    public void deleteArtist(String id) throws IOException {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay artist co id: " + id));

        storageService.delete(artist.getImageUrl(), StorageFolder.ARTIST);
        artistRepository.delete(artist);

    }

    @Transactional
    public Artist updateArtist(String id, MultipartFile img, ArtistRequestDTO artistRequestDTO) throws IOException {
        Artist artist = artistRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy artist với id: " + id));

        // Cập nhật thông tin text
        artist.setName(artistRequestDTO.getName());

        // Nếu có ảnh mới thì xử lý upload và xoá ảnh cũ
        if (img != null && !img.isEmpty()) {
            String oldImageUrl = artist.getImageUrl();

            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                storageService.delete(oldImageUrl, StorageFolder.ARTIST);
            }

            String newImageUrl = storageService.upload(
                    img,
                    StorageFolder.ARTIST
            );

            artist.setImageUrl(newImageUrl);

        } else {
            log.info("Không có ảnh mới, giữ nguyên ảnh cũ.");
        }


        return artistRepository.save(artist);
    }




}
