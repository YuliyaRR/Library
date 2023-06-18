package com.project.library.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.library.core.View;
import com.project.library.core.dto.BookInfo;
import com.project.library.service.api.IBookService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/books")
public class BookController {
    private final IBookService bookService;

    @JsonView(View.InBook.class)
    @PostMapping(path = "/new")
    public ResponseEntity<?> createBook(@RequestBody @Valid BookInfo bookInfo) {
        bookService.createBook(bookInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @JsonView(View.OutBook.class)
    @GetMapping
    public ResponseEntity<List<BookInfo>> getAll() {//TODO м/доб разбивку на страницы
        return new ResponseEntity<>(bookService.getAll(), HttpStatus.OK);
    }

    @JsonView(View.OutBook.class)
    @GetMapping(path = "/{uuid}")
    public ResponseEntity<BookInfo> getById(@PathVariable(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(bookService.getBook(uuid), HttpStatus.OK);
    }

    @JsonView(View.InBook.class)
    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> updateBook(@PathVariable(name = "uuid") UUID uuid,
                                        @PathVariable(name = "dt_update") @Past LocalDateTime dtUpdate,
                                        @RequestBody @Valid BookInfo bookInfo) {
        bookService.updateBook(uuid, dtUpdate, bookInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "uuid") UUID uuid){
        bookService.deleteBook(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
