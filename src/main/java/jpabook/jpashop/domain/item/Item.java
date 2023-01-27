package jpabook.jpashop.domain.item;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stokQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<Category>();

    /**
     * 데이터에 변경이 필요한 경우 setter를 사용하지 않고 메서드를 통해 조작하는 것이 객체지향적이다.
     * */

    //==비즈니스 로직==//
    // 재고 증가
    public void addStock(int quantity) {
        this.stokQuantity = quantity;
    }

    //==비즈니스 로직==//
    // 재고 감소
    public void removeStock(int quantity) {
        int restStock = this.stokQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("need more stock");
        }
        this.stokQuantity = restStock;
    }

}
