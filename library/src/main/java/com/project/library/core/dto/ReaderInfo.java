package com.project.library.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.library.converters.date.LocalDateTimeToLongMillisSerializer;
import com.project.library.converters.date.StringToLocalDateDeserializer;
import com.project.library.core.View;
import com.project.library.validator.annotations.ValidString;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
@Builder(setterPrefix = "set")
@AllArgsConstructor
@NoArgsConstructor
public class ReaderInfo {
    @JsonView({View.OutReader.class, View.OutBook.class})
    private UUID uuid;
    @ValidString
    @JsonView(View.InReader.class)
    private String name;
    @Past
    @JsonView(View.InReader.class)
    @JsonProperty(value = "date_birth")
    @JsonDeserialize(using = StringToLocalDateDeserializer.class)
    private LocalDate dateBirth;
    @ValidString
    @JsonView(View.InReader.class)
    private String address;
    @JsonView(View.OutReader.class)
    private List<BookInfo> books;
    @JsonView(View.OutReader.class)
    @JsonProperty(value = "dt_create")
    @JsonSerialize(using = LocalDateTimeToLongMillisSerializer.class)
    private LocalDateTime dtCreate;
    @JsonView(View.OutReader.class)
    @JsonProperty(value = "dt_update")
    @JsonSerialize(using = LocalDateTimeToLongMillisSerializer.class)
    private LocalDateTime dtUpdate;
}
