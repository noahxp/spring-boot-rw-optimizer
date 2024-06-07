package tw.noah.demo.service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tw.noah.demo.entity.Book;

public interface BookService {
    Page<Book> findAllBooks(Pageable pageable);

    List<Book> findAllBooks();

    void addBook(Book book);
}
