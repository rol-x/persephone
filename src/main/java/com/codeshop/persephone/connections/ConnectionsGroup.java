package com.codeshop.persephone.connections;

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
import net.minidev.json.annotate.JsonIgnore;

import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "groups")
public class ConnectionsGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_seq")
    @SequenceGenerator(name = "groups_seq", sequenceName = "groups_seq", allocationSize = 1)
    @Column(nullable = false)
    private Integer groupId;

    @Column(nullable = false)
    private Integer gameId;

    @JsonIgnore
    @Column(nullable = false)
    private String words;

    @Column(nullable = false)
    private String explanation;

    @Column(nullable = false)
    private String color;

    public Set<String> getWords() {
        return Set.of(words.split(","));
    }

    public boolean matches(Set<String> words) {
        return getWords().containsAll(words) && words.containsAll(getWords());
    }
}
