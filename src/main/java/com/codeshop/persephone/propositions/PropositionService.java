package com.codeshop.persephone.propositions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropositionService {
    private final PropositionRepository propositionRepository;

    public void save(List<ProposedGroup> proposedGroups) {
        propositionRepository.saveAll(proposedGroups);
    }

    public List<ProposedGroup> findAll() {
        return propositionRepository.findAll();
    }
}
