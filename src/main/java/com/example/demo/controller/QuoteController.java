package com.example.demo.controller;

import java.util.List;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.dto.CreateQuoteRequest;
import com.example.demo.entity.Quote;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.service.QuoteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/quotes")
@Tag(name = "Quotes", description = "Manage quotes")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @Operation(summary = "Create a new quote", description = "Accepts a quote object and persists it to the database")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quote created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payload")
    })
    @PostMapping
    public ResponseEntity<Quote> createQuote(@Valid @RequestBody CreateQuoteRequest quoteRequest) {
        Quote savedQuote = quoteService.createQuote(quoteRequest);
        return ResponseEntity.ok(savedQuote);
    }

    @Operation(summary = "Get all quotes", description = "Returns a paginated list of quotes with optional filtering by author")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved quotes")
    })
    @GetMapping
    public ResponseEntity<Page<Quote>> getAllQuotes(
            @Parameter(description = "Author of the quotes to retrieve", example = "cat") @RequestParam(required = false) String author,
            @Parameter(description = "Pagination information", example = "size=10&page=0") @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<Quote> quotes = quoteService.getAllQuotes(author, pageable);
        return ResponseEntity.ok(quotes);
    }

    @Operation(summary = "Get quote by ID", description = "Returns a single quote by its unique identifier")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quote found"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(
            @Parameter(description = "Unique id of the quote to get", example = "1") @PathVariable Long id) {
        Quote quote = quoteService.getQuoteById(id);
        if (quote != null) {
            return ResponseEntity.ok(quote);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Delete a quote", description = "Deletes a quote by its unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Quote deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(
            @Parameter(description = "Unique id of the quote to delete", example = "1") @PathVariable Long id) {
        boolean isDeleted = quoteService.deleteQuote(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a quote", description = "Completely updates an existing quote by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quote updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid payload"),
            @ApiResponse(responseCode = "404", description = "Quote not found")
    })
    public ResponseEntity<Quote> putMethodName(
            @Parameter(description = "Unique id of the quote to update", example = "1") @PathVariable Long id,
            @Valid @RequestBody CreateQuoteRequest toUpdate) {
        Quote updatedQuote = quoteService.updateQuote(id, toUpdate);
        if (updatedQuote != null) {
            return ResponseEntity.ok(updatedQuote);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
