package com.codeshop.persephone.connections;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "games")
public class ConnectionsGame {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long gameId;

    @OneToMany
    private List<ConnectionsGroup> groups;

    @Column(nullable = false)
    private LocalDate date;

    @Column
    private String authors;

    @Column
    private long solvedBy;

    public Optional<ConnectionsGroup> findGroup(Set<String> words) {
        return groups.stream()
            .filter(group -> group.matches(words))
            .findFirst();
    }
}
