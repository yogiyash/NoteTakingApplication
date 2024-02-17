package com.roomboss.notes.service;

import com.roomboss.notes.config.ApplicationConfiguration;
import com.roomboss.notes.dto.NotesDTO;
import com.roomboss.notes.dto.NotesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NotesService {

    @Autowired
    ApplicationConfiguration configuration;

    @Autowired
    private WebClient.Builder webClientBuilder;




    public Mono<NotesResponse> getNotes(){

        return webClientBuilder.build().
                get().uri(configuration.getNotesUrl()).retrieve().bodyToMono(NotesResponse.class);

    }



}
