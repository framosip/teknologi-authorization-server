package br.com.teknologi.as.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorMessage {

    private final String code;
    private final String description;
}
