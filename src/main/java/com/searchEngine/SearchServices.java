package com.searchEngine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SearchServices {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private IndexTableRepository indexTableRepository;

    public void addDocument(Document document) {
        documentRepository.save(document);

        String[] words = document.getContent().split("\\W+");
        for (String word : words) {
            word = word.toLowerCase();
            IndexTable index = new IndexTable(word, document.getId());
            indexTableRepository.save(index);
        }
    }

    public Set<String> search(String query) {
        Set<String> result = new HashSet<>();
        String[] words = query.toLowerCase().split("\\W+");

        for (String word : words) {
            List<IndexTable> indexes = indexTableRepository.findByWord(word);
            Set<String> docIds = new HashSet<>();
            for (IndexTable index : indexes) {
                docIds.add(index.getDocId());
            }

            if (result.isEmpty()) {
                result.addAll(docIds);
            } else {
                result.retainAll(docIds);
            }
        }

        return result;
    }
}
