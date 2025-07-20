package com.codeshop.persephone.connections;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class ConnectionsGame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "games_seq")
    @SequenceGenerator(name = "games_seq", sequenceName = "games_seq", allocationSize = 1)
    @Column(nullable = false)
    private Integer gameId;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "gameId")
    private List<ConnectionsGroup> groups;

    @Column(nullable = false)
    private String date;

    @Column
    private String authors;

    @Column
    private int solvedBy;

    public Optional<ConnectionsGroup> findGroup(Set<String> words) {
        return groups.stream()
            .filter(group -> group.matches(words))
            .findFirst();
    }

    public ConnectionsGame incrementSolvedCount() {
        solvedBy = solvedBy + 1;
        return this;
    }
}
