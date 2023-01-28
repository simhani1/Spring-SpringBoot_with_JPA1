package jpabook.jpashop.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateItemDto {
    private int price;
    private String name;
    private int stockQuantity;
}
