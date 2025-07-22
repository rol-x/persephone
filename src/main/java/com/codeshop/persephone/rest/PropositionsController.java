package com.codeshop.persephone.rest;

import com.codeshop.persephone.propositions.ProposedGroup;
import com.codeshop.persephone.propositions.PropositionService;
import com.codeshop.persephone.rest.dto.ConnectionsProposition;
import com.codeshop.persephone.rest.dto.PropositionApproval;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/propositions")
@RequiredArgsConstructor
public class PropositionsController {
    private final PropositionService propositionService;

    @PostMapping
    public void proposeConnectionsGame(@RequestBody ConnectionsProposition proposition) {
        propositionService.save(
            proposition.toProposedGroups()
        );
    }

    @GetMapping
    public List<ProposedGroup> getConnectionsPropositions() {
        return propositionService.findAll();
    }

    @GetMapping("/{proposedGameId}")
    public List<ProposedGroup> getProposedGame(@PathVariable String proposedGameId) {
        return propositionService.findById(proposedGameId);
    }

    @PatchMapping
    public void updateProposedGroup(@RequestBody ProposedGroup group) {
        propositionService.updateGroup(group);
    }

    @PostMapping("/approve")
    public void approveGameProposition(@RequestBody PropositionApproval approval) {
        propositionService.approveForDate(approval.proposedGameId(), approval.date());
    }

}
