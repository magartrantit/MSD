package com.example.meetings.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class AddParticipantsRequest {

    @NotEmpty
    @Valid
    private List<ParticipantRequest> participants;

    public AddParticipantsRequest() {}

    public AddParticipantsRequest(List<ParticipantRequest> participants) {
        this.participants = participants;
    }

    public List<ParticipantRequest> getParticipants() { return participants; }
    public void setParticipants(List<ParticipantRequest> participants) { this.participants = participants; }
}
