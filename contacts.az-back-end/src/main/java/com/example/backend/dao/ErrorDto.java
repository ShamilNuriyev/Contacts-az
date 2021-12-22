package com.example.backend.dao;

import lombok.Builder;
import lombok.Data;

@Builder
@Data

    public class ErrorDto {
        private String code;
        private String message;

        public ErrorDto() {
        }

        public ErrorDto(String code, String message) {
            this.code = code;
            this.message = message;
        }


}
