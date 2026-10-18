package com.example.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateQuoteRequest {

    @NotBlank
    @Size(max = 1000, message = "Max length of quote is 1000 characters")
    @Schema(description = "Quote text", example = "To be or not to be")
    private String text;

    @NotBlank
    @Size(max = 100, message = "Max length of author is 100 characters")
    @Schema(description = "Author name", example = "William Shakespeare")
    private String author;

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getText() { return text; }
    public void setText(String text) { this.text = text; }
}