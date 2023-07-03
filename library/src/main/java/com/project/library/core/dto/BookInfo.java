package com.project.library.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.library.converters.date.LocalDateTimeToLongMillisSerializer;
import com.project.library.core.View;
import com.project.library.validator.annotations.ValidPublicationYear;
import com.project.library.validator.annotations.ValidString;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {
    @JsonView({View.OutBook.class, View.OutReader.class})
    private UUID uuid;
    @ValidString
    @JsonView({View.InBook.class, View.OutReader.class})
    private String title;
    @JsonView({View.InBook.class, View.OutReader.class})
    private @Valid AuthorInfo author;
    @NotNull
    @JsonView({View.InBook.class, View.OutReader.class})
    @JsonProperty(value = "publication_year")
    @ValidPublicationYear
    private int publicationYear;
    @NotNull(message = "The entered value doesn't exist")
    @JsonView({View.InBook.class, View.OutReader.class})
    private Genre genre;
    @JsonView({View.OutBook.class, View.OutReader.class})
    @JsonProperty(value = "dt_create")
    @JsonSerialize(using = LocalDateTimeToLongMillisSerializer.class)
    private LocalDateTime dtCreate;
    @JsonView({View.OutBook.class, View.OutReader.class})
    @JsonProperty(value = "dt_update")
    @JsonSerialize(using = LocalDateTimeToLongMillisSerializer.class)
    private LocalDateTime dtUpdate;
}
