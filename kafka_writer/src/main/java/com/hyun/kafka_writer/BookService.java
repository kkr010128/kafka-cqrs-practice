package com.hyun.kafka_writer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void saveBook(BookDTO bookDTO) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(
                    "yyyy-MM-dd", Locale.ENGLISH);
            Date published_date = formatter.parse(bookDTO.getPublished_date());
            Book book = Book.builder()
                    .title(bookDTO.getTitle())
                    .author(bookDTO.getAuthor())
                    .category(bookDTO.getCategory())
                    .pages(bookDTO.getPages())
                    .price(bookDTO.getPrice())
                    .published_date(published_date)
                    .description(bookDTO.getDescription())
                    .build();
            bookRepository.save(book);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
