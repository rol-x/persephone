package com.codeshop.persephone.propositions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropositionRepository extends JpaRepository<ProposedGroup, Long> {
}
