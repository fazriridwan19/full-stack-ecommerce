package com.ursklap.ecommerce.utils;

import com.ursklap.ecommerce.dto.responses.ResponseDto;

public class ResponseApiGenerator<T> {
    public static <T> Generator<T> generator() {
        return new Generator<>();
    }

    public static class Generator<T> {
        public Generator() {

        }

        public <T> ResponseDto<T> generate(T data, Integer status, String message) {
            ResponseDto<T> response = new ResponseDto<>();
            response.setData(data);
            response.setStatus(status);
            response.setMessage(message);
            return response;
        }
    }
}
