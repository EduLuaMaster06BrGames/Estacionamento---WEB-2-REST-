package com.example.demo.dto;

public record UpdateProductDto(

        String name,

        String description,

        Boolean available

) {
}