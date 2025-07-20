package com.codeshop.persephone.rest;

import com.codeshop.persephone.propositions.ProposedGroup;
import com.codeshop.persephone.propositions.PropositionService;
import com.codeshop.persephone.rest.dto.ConnectionsProposition;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

}
