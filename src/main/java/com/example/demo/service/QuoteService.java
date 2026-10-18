package com.example.demo.service;

import com.example.demo.dto.CreateQuoteRequest;
import com.example.demo.entity.Quote;
import com.example.demo.repository.QuoteRepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public Quote createQuote(CreateQuoteRequest request) {
        Quote quote = new Quote(request.getText(), request.getAuthor());
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

    public Quote updateQuote(Long id, CreateQuoteRequest updatedQuote) {
        Optional<Quote> optionalQuote = quoteRepository.findById(id);

        if (optionalQuote.isPresent()) {
            Quote existingQuote = optionalQuote.get();
            existingQuote.setAuthor(updatedQuote.getAuthor());
            existingQuote.setText(updatedQuote.getText());
            return quoteRepository.save(existingQuote);
        } else {
            return null;
        }
    }
}