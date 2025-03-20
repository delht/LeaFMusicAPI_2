package de.lht.leafmusic3.controller;

import de.lht.leafmusic3.dto.khac.SearchResultDTO;
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
        return ResponseEntity.ok(searchService.searchByName(keyword));
    }
}
