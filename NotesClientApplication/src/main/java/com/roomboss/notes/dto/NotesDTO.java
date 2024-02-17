package com.roomboss.notes.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NotesDTO {
    private Date createdOn;
    private String title;
    private String content;
}
