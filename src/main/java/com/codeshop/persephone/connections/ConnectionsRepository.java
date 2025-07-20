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
                .color("blue")
                .words("spodnie,sweter,sukienka,spódnica")
                .explanation("Części garderoby na 'S'")
                .build(),
            ConnectionsGroup.builder()
                .connectionsId(1L)
                .groupId(2L)
                .color("green")
                .words("klej,farba,mleko,pasta do zębów")
                .explanation("Kupisz to w tubce")
                .build(),
            ConnectionsGroup.builder()
                .connectionsId(1L)
                .groupId(3L)
                .color("yellow")
                .words("okno,bark,ząb,palec")
                .explanation("Rzeczy, które można wybić")
                .build(),
            ConnectionsGroup.builder()
                .connectionsId(1L)
                .groupId(4L)
                .color("purple")
                .words("ląd,ładny,trawnik,zgoda")
                .explanation("Wyrazy prawne ze zmienioną pierwszą literą")
                .build()
        );
        return new Connections(1L, date, 0, groups);
    }
}
