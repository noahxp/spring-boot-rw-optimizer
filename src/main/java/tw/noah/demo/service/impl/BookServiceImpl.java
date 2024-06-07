package tw.noah.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tw.noah.demo.dao.BookDao;
import tw.noah.demo.entity.Book;
import tw.noah.demo.service.BookService;

@Service
@RequiredArgsConstructor
@Log4j2
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    public Page<Book> findAllBooks(Pageable pageable) {
        List<Book> ret = bookDao.findAll(PageRequest.of(0,100)).stream().collect(Collectors.toList());
        return bookDao.findAll(pageable);
    }

    public void addBook(Book book) {
        bookDao.save(book);
    }
}
