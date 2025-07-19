package com.codeshop.persephone.connections;

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
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "connection_groups")
@Entity
public class ConnectionsGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long groupId;

    @Column(nullable = false)
    private Long connectionsId;

    @Column(nullable = false)
    private String explanation;

    @JsonIgnore
    @Column(nullable = false)
    private String words;

    @Column(nullable = false)
    private String color;

    public List<String> getWordsList() {
        return List.of(words.split(","));
    }
}
