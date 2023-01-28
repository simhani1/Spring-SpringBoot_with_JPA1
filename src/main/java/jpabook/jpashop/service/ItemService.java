package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.reposiotry.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    // 변경감지를 사용하는 방법
    public void updateItem(Long itemId, UpdateItemDto updateItemDto) {

        // DTO 객체를 사용하는 방법
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setPrice(updateItemDto.getPrice());
//        findItem.setName(updateItemDto.getName());
//        findItem.setStockQuantity(updateItemDto.getStockQuantity());

        // 변경 함수를 사용하는 방법(추천)
        Item findItem = itemRepository.findOne(itemId);
        findItem.change(updateItemDto.getName(), updateItemDto.getPrice(), updateItemDto.getStockQuantity());
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }

}
