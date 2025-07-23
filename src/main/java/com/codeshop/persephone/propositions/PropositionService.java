package com.codeshop.persephone.propositions;

import com.codeshop.persephone.connections.ConnectionsGame;
import com.codeshop.persephone.connections.ConnectionsGroup;
import com.codeshop.persephone.connections.ConnectionsGroupRepository;
import com.codeshop.persephone.connections.ConnectionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PropositionService {
    private final PropositionRepository propositionRepository;
    private final ConnectionsRepository connectionsRepository;
    private final ConnectionsGroupRepository connectionsGroupRepository;

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

    /**
     * Approves a proposed game for a specific date.
     * Creates a ConnectionsGame from the proposed groups and saves it with a number
     * that is 1 higher than the counter of a game with the previous date.
     * 
     * @param proposedGameId the ID of the proposed game to approve
     * @param date the date for which to approve the game
     */
    public void approveForDate(String proposedGameId, String date) {
        var proposedGroups = findById(proposedGameId);

        if (connectionsRepository.findByDate(date).isPresent()) {
            throw new IllegalArgumentException("A game for this date already exists");
        }
        
        int nextNumber = connectionsRepository.findByDate(getPreviousDate(date))
                             .map(ConnectionsGame::getNumber)
                             .orElse(0) + 1;
        
        ConnectionsGame game = ConnectionsGame.builder()
            .authors(getAuthors(proposedGroups))
            .date(date)
            .number(nextNumber)
            .build();
        
        // Save the game to get its ID
        ConnectionsGame savedGame = connectionsRepository.save(game);
        
        // Create and save ConnectionsGroup entities with the gameId set
        proposedGroups.forEach(proposedGroup -> {
            ConnectionsGroup groupWithGameId = ConnectionsGroup.builder()
                .gameId(savedGame.getGameId())
                .words(proposedGroup.getWordsString())
                .explanation(proposedGroup.getExplanation())
                .color(proposedGroup.getColor())
                .build();
            
            connectionsGroupRepository.save(groupWithGameId);
        });
    }

    private static String getAuthors(List<ProposedGroup> proposedGroups) {
        return proposedGroups.stream()
            .map(ProposedGroup::getAuthor)
            .distinct()
            .collect(Collectors.joining(", "));
    }

    private static String getPreviousDate(String date) {
        return LocalDate.parse(date).minusDays(1).toString();
    }

    public void upsertGroup(ProposedGroup group) {
        propositionRepository.save(group);
    }
}
