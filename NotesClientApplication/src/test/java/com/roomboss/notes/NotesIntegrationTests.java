package com.roomboss.notes;

import com.roomboss.notes.config.ApplicationConfiguration;
import com.roomboss.notes.dto.NotesDTO;
import com.roomboss.notes.dto.NotesResponse;
import com.roomboss.notes.service.NotesService;
import org.assertj.core.api.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class NotesIntegrationTests {


    @Autowired
    NotesService notesService;

    @Autowired
    ApplicationConfiguration configuration;

    @Autowired
    WebClient.Builder webClientBuilder;





    @Test
    public void createANote(){


        String title = "a test note";
        String content = "test content";
        NotesDTO note  = NotesDTO.builder().title(title).content(content).build();
        NotesResponse response = webClientBuilder.build().post().uri(configuration.getNotesUrl()).
                contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
                        .bodyValue(note).retrieve().bodyToMono(NotesResponse.class).block();
        Assertions.assertNotNull(response.getNotes());
        Assertions.assertNotNull(response.getNotes().get(0));
        Assertions.assertEquals(title,response.getNotes().get(0).getTitle());
        Assertions.assertEquals(content, response.getNotes().get(0).getContent());
        System.out.println("notes created successfully");




    }

    @Test
    public void retrieveNotes(){
        notesService.getNotes().doOnSuccess(response -> {
            Assertions.assertNotNull(response.getNotes());
            Assertions.assertTrue(response.getNotes().size()>0);
            response.getNotes().stream().forEach(System.out::println);

        }).doOnError(e -> Fail.fail(e.getMessage())).block();

    }


}
