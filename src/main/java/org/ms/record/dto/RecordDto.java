package org.ms.record.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RecordDto(

        @NotBlank
        @Size(min = 2, max = 100)
        String name,

        @NotBlank
        @Size(min = 2, max = 100)
        String artist,

        @NotNull
        @Size(max = 4)
        Long year,

        @Size(min= 2, max = 50)
        String genre,

        @NotBlank
        @Pattern(regexp = "Vinyl|CD|Digital|Cassette|8-track", message = "Invalid format. Allowed formats are: Vinyl, CD, Digital, Cassette, 8-track.")
        String format
){}
