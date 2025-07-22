package com.codeshop.persephone.propositions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PropositionRepository extends JpaRepository<ProposedGroup, Integer> {
    List<ProposedGroup> findByProposedGameId(String proposedGameId);
}
