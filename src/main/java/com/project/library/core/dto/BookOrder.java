package com.project.library.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;
import java.util.UUID;
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookOrder {
    @NotNull
    @JsonProperty(value = "uuid_reader")
    private UUID uuidReader;
    @NotNull
    private Set<@NotNull BookInfo> books;
}
