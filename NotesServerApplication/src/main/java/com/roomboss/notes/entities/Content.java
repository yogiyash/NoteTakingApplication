package com.roomboss.notes.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Content {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    @Column(length = 1000)
    @Size(max = 1000)
    private String text;

    @OneToOne(mappedBy = "content")
    private Note note;

}
