package com.codeshop.persephone.rest.dto;

import com.codeshop.persephone.propositions.ProposedGroup;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public record ConnectionsProposition(String author, List<GroupProposition> groups) {

    record GroupProposition(Set<String> words, String explanation, String color) {}

    public List<ProposedGroup> toProposedGroups() {
        var proposedGameId = generateProposalId();
        return groups.stream()
            .map(group ->
                     ProposedGroup.builder()
                         .proposedGameId(proposedGameId)
                         .words(String.join(",", group.words()))
                         .color(group.color())
                         .explanation(group.explanation())
                         .author(author)
                         .build()
            ).toList();
    }

    private String generateProposalId() {
        String dateStamp = Instant.now()
            .atZone(ZoneId.of("Europe/Warsaw"))
            .format(DateTimeFormatter.ofPattern("yyMMdd"));
        return "%s-%s".formatted(
            dateStamp,
            UUID.randomUUID().toString().substring(0, 4)
        );
    }
}
