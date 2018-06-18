package com.syun.springboottest03;

public interface BookRepository {
    Book getByIsbn(String isbn);
}
