package com.project.library.core.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.project.library.core.View;
import com.project.library.validator.annotations.ValidString;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AuthorInfo {
    @ValidString
    @JsonView({View.OutBook.class, View.OutReader.class})
    private String name;
}
