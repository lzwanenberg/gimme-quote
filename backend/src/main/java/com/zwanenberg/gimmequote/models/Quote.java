package com.zwanenberg.gimmequote.models;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quote {
    private String author;
    private String content;
}
