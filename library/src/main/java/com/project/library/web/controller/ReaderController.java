package com.project.library.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.library.core.View;
import com.project.library.core.dto.BookOrder;
import com.project.library.core.dto.ReaderInfo;
import com.project.library.service.api.IReaderService;
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
@RequestMapping(path = "/readers")
public class ReaderController {
    private final IReaderService readerService;

    @JsonView(View.InReader.class)
    @PostMapping(path = "/new")
    public ResponseEntity<?> createReader(@RequestBody @Valid ReaderInfo readerInfo) {
        readerService.createReader(readerInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @JsonView(View.OutReader.class)
    @GetMapping
    public ResponseEntity<List<ReaderInfo>> getAll() {
        return new ResponseEntity<>(readerService.getAll(), HttpStatus.OK);
    }

    @JsonView(View.OutReader.class)
    @GetMapping(path = "/{uuid}")
    public ResponseEntity<ReaderInfo> getById(@PathVariable(name = "uuid") UUID uuid) {
        return new ResponseEntity<>(readerService.getReader(uuid), HttpStatus.OK);
    }

    @JsonView(View.InReader.class)
    @PutMapping(path = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<?> updateReader(@PathVariable(name = "uuid") UUID uuid,
                                        @PathVariable(name = "dt_update") @Past LocalDateTime dtUpdate,
                                        @RequestBody @Valid ReaderInfo readerInfo) {
        readerService.updateReaderInfo(uuid, dtUpdate, readerInfo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{uuid}")
    public ResponseEntity<?> deleteBook(@PathVariable(name = "uuid") UUID uuid) {
        readerService.deleteReader(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/take")
    public ResponseEntity<?> takeBooks(@RequestBody @Valid BookOrder bookOrder) {
        readerService.writeBooksToReader(bookOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(path = "/return")
    public ResponseEntity<?> returnBooks(@RequestBody @Valid BookOrder bookOrder) {
        readerService.deleteReturnedBooksFromReader(bookOrder);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
