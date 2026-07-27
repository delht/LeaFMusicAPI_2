package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.dto.song.SongRequestDTO;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.exception.AppException;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor //bo autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongService {

    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final ArtistRepository artistRepository;

    public Page<SongDTO> getAllSongs(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Song> songsPage = songRepository.findAll(pageable);
        return songsPage.map(songMapper::toDTO);
    }

    public SongDTO getSongById(int id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy bài hát"));

        song.setPlay(song.getPlay() + 1);
        songRepository.save(song);

        return songMapper.toDTO(song);

    }

    //Lấy danh sách bài hát ngẫu nhiên (5 hoặc 10 bài)
    public List<SongDTO> getRandomSongs(int limit) {
        List<Song> songs = songRepository.findRandomSongs(limit)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy bài hát ngẫu nhiên"));
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByArtist(int artistId) {
        List<Song> songs = songRepository.findByIdArtist(artistId)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy bài hát của nghệ sĩ"));
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByAlbum(int albumId) {
        List<Song> songs = songRepository.findByIdAlbum(albumId)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy bài hát của album"));
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByGenre(int genreId) {
        List<Song> songs = songRepository.findByIdGenre(genreId)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy bài hát của thể loại"));
        return songMapper.toDTOs(songs);
    }

//    ===============================================================================================

    private final StorageService storageService;

    @Transactional
    public Song createSong(MultipartFile img, MultipartFile audio, SongRequestDTO songRequestDTO) throws IOException {
        try {

            String fileUrlImg = storageService.upload(img, StorageFolder.SONG_IMG);
            String fileUrlAudio = storageService.upload(audio, StorageFolder.SONG_AUDIO);

            Song song = new Song();
            song.setName(songRequestDTO.getName());
            song.setPlay(0);
            song.setReleaseDate(songRequestDTO.getReleaseDate());
            song.setIdArtist(songRequestDTO.getIdArtist());
            song.setIdAlbum(songRequestDTO.getIdAlbum());
            song.setIdGenre(songRequestDTO.getIdGenre());
            song.setUploadBy(songRequestDTO.getUploadBy());

            song.setImageUrl(fileUrlImg);
            song.setFileUrl(fileUrlAudio);

            return songRepository.save(song);
        } catch (IOException e) {
            throw new AppException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Upload thất bại."
            );
        }
    }

    public void deleteSong(Integer id) throws IOException {

        Song song = songRepository.findById(id)
                .orElseThrow(()->new AppException(HttpStatus.NOT_FOUND, "Khong tim thay bai hast:" + id));

        storageService.delete(song.getImageUrl(), StorageFolder.SONG_IMG);
        storageService.delete(song.getFileUrl(), StorageFolder.SONG_AUDIO);

        songRepository.delete(song);
        log.info("Song với ID {} đã được xóa thành công.", id);
    }

    @Transactional
    public Song updateSong(Integer id, MultipartFile img, MultipartFile audio, SongRequestDTO songRequestDTO) throws IOException {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND, "Không tìm thấy song với id: " + id));

        song.setName(songRequestDTO.getName());
        song.setPlay(songRequestDTO.getPlay());
        song.setReleaseDate(songRequestDTO.getReleaseDate());
        song.setIdArtist(songRequestDTO.getIdArtist());
        song.setIdAlbum(songRequestDTO.getIdAlbum());
        song.setIdGenre(songRequestDTO.getIdGenre());
        song.setUploadBy(song.getUploadBy());

        // Xử lý ảnh mới
        if (img != null && !img.isEmpty()) {
            String oldImageUrl = song.getImageUrl();

            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                storageService.delete(oldImageUrl, StorageFolder.SONG_IMG);
            }

            String newImageUrl = storageService.upload(img, StorageFolder.SONG_IMG);

            song.setImageUrl(newImageUrl);
        }

        // Xử lý audio mới
        if (audio != null && !audio.isEmpty()) {
            String oldAudioUrl = song.getFileUrl();

            if (oldAudioUrl != null && !oldAudioUrl.isEmpty()) {
                storageService.delete(oldAudioUrl, StorageFolder.SONG_AUDIO);
            }

            String newAudioUrl = storageService.upload(audio, StorageFolder.SONG_AUDIO);

            song.setFileUrl(newAudioUrl);
        }

        return songRepository.save(song);
    }

//    ===============================================================================================

    //TODO ======================= chưa xử lý

    public List<SongDTO> getSongbyUser(String idUser){
        List<Song> songs = songRepository.findByUploadBy(idUser);
        return songMapper.toDTOs(songs);
    }

//    ===============================================================================================

    public List<Song> getSuggestedSongs(List<Integer> artistIds, List<Integer> genreIds, int limit) {
        List<Song> matchedSongs = songRepository.findByIdArtistInOrIdGenreIn(artistIds, genreIds);
        Collections.shuffle(matchedSongs); // Trộn để tạo ds ngẫu nhiên
        return matchedSongs.stream().limit(limit).collect(Collectors.toList());
    }




}
