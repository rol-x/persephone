package com.codeshop.persephone.connections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionsGroupRepository extends JpaRepository<ConnectionsGroup, Integer> {
}