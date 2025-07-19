package com.codeshop.persephone.connections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConnectionsRepository extends JpaRepository<Connections, Integer> {
    default Connections findByDate(LocalDate date) {
        var groups = List.of(
            ConnectionsGroup.builder()
                .connectionsId(1L)
                .groupId(1L)
                .color("purple")
                .words("pan,fan,talk,bus")
                .explanation("Także czasowniki w j. angielskim")
                .build(),
            ConnectionsGroup.builder()
                .connectionsId(1L)
                .groupId(1L)
                .color("green")
                .words("kwas,bot,krup,bar")
                .explanation("Początki nazw polskich zup")
                .build(),
            ConnectionsGroup.builder()
                .connectionsId(1L)
                .groupId(1L)
                .color("blue")
                .words("potop,oko,radar,sos")
                .explanation("Palindromy")
                .build(),
            ConnectionsGroup.builder()
                .connectionsId(1L)
                .groupId(1L)
                .color("yellow")
                .words("zasada,auto,modny,mądry")
                .explanation("SMART")
                .build()
        );
        return new Connections(1L, date, 0, groups);
    }
}
