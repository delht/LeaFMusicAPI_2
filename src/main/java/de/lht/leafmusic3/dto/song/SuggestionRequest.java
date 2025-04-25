package de.lht.leafmusic3.dto.song;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SuggestionRequest {
    private List<Integer> artistIds;
    private List<Integer> genreIds;
}
