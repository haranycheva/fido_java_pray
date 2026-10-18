package com.example.demo.service;

import com.example.demo.entity.Quote;
import com.example.demo.repository.QuoteRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service 
public class QuoteService {

    private final QuoteRepository quoteRepository;


    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote createQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public Page<Quote> getAllQuotes(String author, Pageable pageable) {
        if (author != null && !author.isEmpty()) {
            return quoteRepository.findByAuthorContainingIgnoreCase(author, pageable);
        }
        return quoteRepository.findAll(pageable);
    }

    public Quote getQuoteById(Long id) {
        return quoteRepository.findById(id).orElse(null);
    }
    
    public boolean deleteQuote(Long id) {
        if (quoteRepository.existsById(id)) {
            quoteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}