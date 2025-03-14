package com.desafio.itau.desafioItau.infra;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;

@Builder
public record ApiError(
        @JsonFormat
        OffsetDateTime offsetDateTime,

        int code,

        String status,

        List<String>errors
) { }
