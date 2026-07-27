package de.lht.leafmusic3.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.lht.leafmusic3.dto.ApiResponse;
import de.lht.leafmusic3.dto.album.Album2DTO;
import de.lht.leafmusic3.dto.album.AlbumRequestDTO;
import de.lht.leafmusic3.dto.artist.Artist2DTO;
import de.lht.leafmusic3.dto.artist.ArtistDTO;
import de.lht.leafmusic3.dto.artist.ArtistRequestDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.Album;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.exception.AppException;
import de.lht.leafmusic3.service.ArtistService;
//import de.lht.leafmusic3.service.SongService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/artists")
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("/v1/all")
    public ResponseEntity<ApiResponse<Page<ArtistDTO>>> getArtists(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Danh sách tất cả nghệ sĩ",
                        artistService.getAllArtists(page, size)
                )
        );
    }

    @GetMapping("/v2/all")
    public ResponseEntity<ApiResponse<Page<Artist2DTO>>> getAllArtistsV2(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Danh sách tất cả nghệ sĩ",
                        artistService.getAllArtist2(page, size)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ArtistDTO>> getArtistById(@PathVariable String id) {
        return ResponseEntity.ok(
                new ApiResponse<>(
                        HttpStatus.OK,
                        "Lấy thông tin tác giả thành công",
                        artistService.getArtistById(id)
                )
        );
    }

    //TODO Sửa random
    @GetMapping("/random")
    public List<ArtistDTO> getRandomArtists(@RequestParam(defaultValue = "5") int limit) {
        return artistService.getRandomArtists(limit);
    }

//    ===================================================================

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/auth/add")
    public ResponseEntity<?> addArtist(
            @RequestParam("img") MultipartFile img,
            @RequestParam("artist") String artistRequestJson) throws IOException {

        ArtistRequestDTO artistRequest =
                objectMapper.readValue(artistRequestJson, ArtistRequestDTO.class);

        Artist artist = artistService.addArtist(img, artistRequest);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        HttpStatus.CREATED,
                        "Thêm artist thành công.",
                        artist
                ));
    }

    @DeleteMapping("/auth/delete/id/{id}")
    public ResponseEntity<?> deleteArtist(@PathVariable("id") String id) throws IOException {
        artistService.deleteArtist(id);

        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK,
                "Xóa artist thành công.",
                null
        ));

    }

    @PutMapping("/auth/update/id/{id}")
    public ResponseEntity<?> updateArtist(
            @PathVariable String id,
            @RequestParam(value = "img", required = false) MultipartFile img,
            @RequestParam("artist") String artistRequestJson
    ) throws IOException {

        ArtistRequestDTO artistRequest =
                objectMapper.readValue(artistRequestJson, ArtistRequestDTO.class);

        Artist updatedArtist = artistService.updateArtist(id, img, artistRequest);

        return ResponseEntity.ok(new ApiResponse<>(
                HttpStatus.OK,
                "Cập nhật artist thành công.",
                updatedArtist
        ));
    }

}
