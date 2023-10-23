package hello.itemservice;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;

    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() {
        itemRepository.save(new Item("Mango", 4900, 10));
        itemRepository.save(new Item("Apple", 5500, 15));
        itemRepository.save(new Item("Banana", 10000, 17));
        itemRepository.save(new Item("Orange", 18900, 30));
    }
}