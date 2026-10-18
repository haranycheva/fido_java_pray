package com.example.demo.controller;

import java.util.List;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Quote;
import com.example.demo.repository.QuoteRepository;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private final QuoteRepository quoteRepository;

    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @PostMapping
    public ResponseEntity<Quote> createQuote(@RequestBody Quote quoteRequest) {
        Quote savedQuote = quoteRepository.save(quoteRequest);
        return ResponseEntity.ok(savedQuote);
    }

    // @GetMapping
    // public ResponseEntity<List<Quote>> getAllQuotes() {
    // List<Quote> quotes = quoteRepository.findAll();
    // return ResponseEntity.ok(quotes);
    // }

    @GetMapping
    public ResponseEntity<Page<Quote>> getAllQuotes(
            @RequestParam(required = false) String author,
            @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<Quote> quotes;
        if (author != null && !author.isBlank()) {
            quotes = quoteRepository.findByAuthorContainingIgnoreCase(author, pageable);
        }

        else {
            quotes = quoteRepository.findAll(pageable);
        }

        return ResponseEntity.ok(quotes);
    }
}
