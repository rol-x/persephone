package com.codeshop.persephone.propositions;


import com.codeshop.persephone.connections.ConnectionsGroup;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class ProposedGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proposed_groups_seq")
    @SequenceGenerator(name = "proposed_groups_seq", sequenceName = "proposed_groups_seq", allocationSize = 1)
    private Integer proposedGroupId;

    @Column
    private String proposedGameId;

    @Column
    private String words;

    @Column
    private String explanation;

    @Column
    private String color;

    @Column
    private String author;

    public String getWordsString() {
        return words;
    }

    public Set<String> getWords() {
        return Set.of(words.split(","));
    }

    public ConnectionsGroup toConnectionsGroup() {
        return ConnectionsGroup.builder()
            .explanation(explanation)
            .words(words)
            .color(color)
            .build();
    }
}
