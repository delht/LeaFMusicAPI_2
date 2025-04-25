package de.lht.leafmusic3.service;

import de.lht.leafmusic3.cloud.GetPubID;
import de.lht.leafmusic3.cloud.repo.DeleteFile;
import de.lht.leafmusic3.cloud.repo.UploadFile;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.dto.song.SongRequestDTO;
import de.lht.leafmusic3.entity.Artist;
import de.lht.leafmusic3.entity.Song;
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
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor //bo autowired
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SongService {


    private final SongRepository songRepository;
    private final SongMapper songMapper;
    private final ArtistRepository artistRepository;

    public List<SongDTO> getAllSongs() {
        List<Song> songs = songRepository.findAll();
        return songMapper.toDTOs(songs);
    }

    public SongDTO getSongById(int id) {
        Song song = songRepository.findById(id);

        song.setPlay(song.getPlay() + 1);
        songRepository.save(song);

        return songMapper.toDTO(song);

    }

    //Lấy danh sách bài hát ngẫu nhiên (5 hoặc 10 bài)
    public List<SongDTO> getRandomSongs(int limit) {
        List<Song> songs = songRepository.findRandomSongs(limit);
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByArtist(int artistId) {
        List<Song> songs = songRepository.findByIdArtist(artistId);
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByAlbum(int albumId) {
        List<Song> songs = songRepository.findByIdAlbum(albumId);
        return songMapper.toDTOs(songs);
    }

    public List<SongDTO> findSongsByGenre(int genreId) {
        List<Song> songs = songRepository.findByIdGenre(genreId);
        return songMapper.toDTOs(songs);
    }

//    ===============================================================================================

    private final DeleteFile deleteFile;
    private final UploadFile uploadFile;
    private final GetPubID getPubID;

    @Transactional
    public Song createSong(MultipartFile img, MultipartFile audio, SongRequestDTO songRequestDTO) throws IOException {
        try {
            String folderImg = "LeaFMusic2/Images/Song/";
            String fileUrlImg = uploadFile.uploadFile(img, folderImg);

            String folderAudio = "LeaFMusic2/Audio/Song/";
            String fileUrlAudio = uploadFile.uploadFile(audio, folderAudio);

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
            throw new IOException("Lỗi khi xử lý file", e);
        }
    }

    public void deleteSong(Integer id) throws IOException {

        Song song = songRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Khong tim thay bai hast:" + id));

        // Xử lý xóa ảnh
        String folderImg = "LeaFMusic2/Images/Song/";
        String fileUrlImg = song.getImageUrl();
        if (fileUrlImg != null && !fileUrlImg.isEmpty()) {
            String publicIdImg = getPubID.layPublicIdTuURL(fileUrlImg, folderImg);
            deleteFile.deleteFile(publicIdImg);
            log.info("Đã xóa ảnh: {}", publicIdImg);
        }

        // Xử lý xóa audio
        String folderAudio = "LeaFMusic2/Audio/Song/";
        String fileUrlAudio = song.getFileUrl();
        if (fileUrlAudio != null && !fileUrlAudio.isEmpty()) {
            String publicIdAudio = getPubID.layPublicIdTuURL(fileUrlAudio, folderAudio);
            deleteFile.deleteFile(publicIdAudio);
            log.info("Đã xóa file audio: {}", publicIdAudio);
        }

        songRepository.delete(song);
        log.info("Song với ID {} đã được xóa thành công.", id);
    }

    @Transactional
    public Song updateSong(Integer id, MultipartFile img, MultipartFile audio, SongRequestDTO songRequestDTO) throws IOException {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy song với id: " + id));

        song.setName(songRequestDTO.getName());
        song.setPlay(songRequestDTO.getPlay());
        song.setReleaseDate(songRequestDTO.getReleaseDate());
        song.setIdArtist(songRequestDTO.getIdArtist());
        song.setIdAlbum(songRequestDTO.getIdAlbum());
        song.setIdGenre(songRequestDTO.getIdGenre());
        song.setUploadBy(song.getUploadBy());

        // Xử lý ảnh mới
        if (img != null && !img.isEmpty()) {
            String folderImg = "LeaFMusic2/Images/Song/";
            String oldImageUrl = song.getImageUrl();
            if (oldImageUrl != null && !oldImageUrl.isEmpty()) {
                String publicIdImg = getPubID.layPublicIdTuURL(oldImageUrl, folderImg);
                deleteFile.deleteFile(publicIdImg);
            }
            String newImageUrl = uploadFile.uploadFile(img, folderImg);
            song.setImageUrl(newImageUrl);
        }

        // Xử lý audio mới
        if (audio != null && !audio.isEmpty()) {
            String folderAudio = "LeaFMusic2/Audio/Song/";
            String oldAudioUrl = song.getFileUrl();
            if (oldAudioUrl != null && !oldAudioUrl.isEmpty()) {
                String publicIdAudio = getPubID.layPublicIdTuURL(oldAudioUrl, folderAudio);
                deleteFile.deleteFile(publicIdAudio);
            }
            String newAudioUrl = uploadFile.uploadFile(audio, folderAudio);
            song.setFileUrl(newAudioUrl);
        }

        return songRepository.save(song);
    }

//    ===============================================================================================

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
