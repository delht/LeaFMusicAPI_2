package de.lht.leafmusic3.service;

import de.lht.leafmusic3.dto.customsonglist.CustomSonglistDTO;
import de.lht.leafmusic3.dto.song.SongDTO;
import de.lht.leafmusic3.entity.CustomSonglist;
import de.lht.leafmusic3.entity.Song;
import de.lht.leafmusic3.mapper.CustomSonglistMapper;
import de.lht.leafmusic3.mapper.SongMapper;
import de.lht.leafmusic3.repository.CustomSonglistRepository;
import de.lht.leafmusic3.repository.SongRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomSonglistService {

    private final CustomSonglistRepository customSonglistRepository;
    private final CustomSonglistMapper customSonglistMapper;
    private final SongRepository songRepository;
    private final SongMapper songMapper;

//    public List<CustomSonglistDTO> getByIdList(int idList) {
//        List<CustomSonglist> customSonglists = customSonglistRepository.findByIdList(idList);
//        return customSonglistMapper.toDtos(customSonglists);
//    }

    public List<SongDTO> getSongsByIdList(int idList) {
        List<CustomSonglist> customSonglists = customSonglistRepository.findByIdList(idList);
        List<Integer> songIds = customSonglists.stream()
                .map(CustomSonglist::getIdSong)
                .collect(Collectors.toList());
        List<Song> songs = songRepository.findAllById(songIds);
        return songMapper.toDTOs(songs);
    }

//    ==========================================================================


    public String addSongToList(int idList, int idSong) {
        if (customSonglistRepository.existsByIdListAndIdSong(idList, idSong)) {
            return "Bài hát đã tồn tại trong danh sách.";
        }
        CustomSonglist item = new CustomSonglist();
        item.setIdList(idList);
        item.setIdSong(idSong);

        customSonglistRepository.save(item);
        return "Thêm bài hát thành công.";
    }

    @Transactional
    public String removeSongFromList(int idList, int idSong) {
        if (!customSonglistRepository.existsByIdListAndIdSong(idList, idSong)) {
            return "Bài hát không tồn tại trong danh sách.";
        }
        customSonglistRepository.deleteByIdListAndIdSong(idList, idSong);
        return "Xóa bài hát thành công.";
    }



}
