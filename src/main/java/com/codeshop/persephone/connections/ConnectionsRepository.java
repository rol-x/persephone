package com.codeshop.persephone.connections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectionsRepository extends JpaRepository<ConnectionsGame, Long> {

    Optional<ConnectionsGame> findByDate(String date);
}
