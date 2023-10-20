package hello.itemservice.domain.item;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FAST   : 로켓 배송
 * NORMAL : 일반 배송
 * SLOW   : 느린 배송
 * 해당 클래스는 배송방식을 고객에게 보여준다
 * code는 시스템에서 전달하는 값
 * displayName은 고객에서 전달하는 값
 */
@Data
@AllArgsConstructor
public class DeliveryCode {
    private String code;
    private String displayName;
}
