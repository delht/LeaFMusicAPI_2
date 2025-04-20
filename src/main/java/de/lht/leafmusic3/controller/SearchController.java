package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.search.SearchResultDTO;
import de.lht.leafmusic3.dto.search.SearchResultDTO2;
import de.lht.leafmusic3.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public ResponseEntity<SearchResultDTO> search(@RequestParam String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            // Trả về danh sách rỗng khi từ khóa trống
            return ResponseEntity.ok(new SearchResultDTO());
        }

        return ResponseEntity.ok(searchService.searchByName(keyword));
    }


    @GetMapping("/v2")
    public ResponseEntity<SearchResultDTO2> search2(@RequestParam String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            // Trả về danh sách rỗng khi từ khóa trống
            return ResponseEntity.ok(new SearchResultDTO2());
        }

        return ResponseEntity.ok(searchService.searchByName2(keyword));
    }

}
