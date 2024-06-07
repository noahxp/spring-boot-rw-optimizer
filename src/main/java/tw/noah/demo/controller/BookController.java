package tw.noah.demo.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.noah.demo.entity.Book;
import tw.noah.demo.service.BookService;

@Log4j2
@RestController
@RequestMapping("/apis/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/all")
    public List<Book> findAll() {
        return bookService.findAllBooks();
    }

    @GetMapping("/all/{page}")
    public Page findAllBooks(@PathVariable(name = "page") int page) {
        return bookService.findAllBooks(PageRequest.of(page, 20));
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

}
