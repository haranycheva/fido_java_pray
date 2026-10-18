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

import com.example.demo.entity.Quote;
import com.example.demo.repository.QuoteRepository;
import com.example.demo.service.QuoteService;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private final QuoteService quoteService;

    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quoteRequest) {
        Quote savedQuote = quoteService.createQuote(quoteRequest);
        return ResponseEntity.ok(savedQuote);
    }

    @GetMapping
    public ResponseEntity<Page<Quote>> getAllQuotes(
            @RequestParam(required = false) String author,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<Quote> quotes = quoteService.getAllQuotes(author, pageable);
        return ResponseEntity.ok(quotes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quote> getQuoteById(@PathVariable Long id) {
        Quote quote = quoteService.getQuoteById(id);
        if (quote != null) {
            return ResponseEntity.ok(quote);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuote(@PathVariable Long id) {
        boolean isDeleted = quoteService.deleteQuote(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
