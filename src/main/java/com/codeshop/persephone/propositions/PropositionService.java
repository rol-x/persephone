package com.codeshop.persephone.propositions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropositionService {
    private final PropositionRepository propositionRepository;

    public void save(List<ProposedGroup> proposedGroups) {
        proposedGroups.stream()
                .filter(group -> group.getWords().size() == 4)
                .forEach(propositionRepository::save);
    }

    public List<ProposedGroup> findAll() {
        return propositionRepository.findAll();
    }
}
