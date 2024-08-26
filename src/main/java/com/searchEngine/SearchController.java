package com.searchEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchServices searchService;

    @PostMapping("/add")
    public void addDocument(@RequestBody Document document) {
        searchService.addDocument(document);
    }

    @GetMapping
    public Set<String> search(@RequestParam String query) {
        return searchService.search(query);
    }
}
