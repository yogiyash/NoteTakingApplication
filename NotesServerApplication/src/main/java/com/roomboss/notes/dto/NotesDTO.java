package com.roomboss.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotesDTO {

    private Date createdOn;
    @NotBlank
    private String title;
    @NotBlank
    @Size(max = 1000)
    private String content;
}
