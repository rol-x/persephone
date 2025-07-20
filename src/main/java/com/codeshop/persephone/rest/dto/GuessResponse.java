package com.codeshop.persephone.rest.dto;

import com.codeshop.persephone.connections.ConnectionsGroup;

public record GuessResponse(boolean correct, String explanation, String color) {

    public static GuessResponse correct(ConnectionsGroup connectionsGroup) {
        return new GuessResponse(true, connectionsGroup.getExplanation(), connectionsGroup.getColor());
    }

    public static GuessResponse incorrect() {
        return new GuessResponse(false, null, null);
    }
}
