package com.codeshop.persephone.propositions;

import com.codeshop.persephone.connections.ConnectionsGame;
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

    public List<ProposedGroup> findById(String proposedGameId) {
        return propositionRepository.findByProposedGameId(proposedGameId);
    }

    public void approveForDate(String proposedGameId, String date) {
        var proposedGroups = findById(proposedGameId);
        var groups = proposedGroups.stream()
            .map(ProposedGroup::toConnectionsGroup)
            .toList();
        ConnectionsGame game = ConnectionsGame.builder()
            .authors(proposedGroups.getFirst().getAuthor())
            .date(date)
            .groups(groups)
            .build();
        // complete me!
    }

    public void updateGroup(ProposedGroup group) {
        propositionRepository.save(group);
    }
}
