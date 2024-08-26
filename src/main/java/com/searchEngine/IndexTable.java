package com.searchEngine;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "index_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IndexTable {

    @Id
    private String word;
    private String docId;
}
