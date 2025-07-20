package com.codeshop.persephone.propositions;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ProposedGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long proposedGroupId;

    @Column
    private UUID proposedGameId;

    @Column
    private String words;

    @Column
    private String explanation;

    @Column
    private String color;

    @Column
    private String author;

    public Set<String> getWords() {
        return Set.of(words.split(","));
    }
}
