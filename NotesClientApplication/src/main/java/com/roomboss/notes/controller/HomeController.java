package com.roomboss.notes.controller;

import com.roomboss.notes.dto.NotesDTO;
import com.roomboss.notes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.Rendering;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Controller

public class HomeController {
    @Autowired
    NotesService notesService;


    @GetMapping("/")
    public Mono<String> sample(Model model){
        return notesService.getNotes().doOnSuccess(
            notesResponse->model.addAttribute("items",notesResponse.getNotes())).thenReturn("table");
    }




}


