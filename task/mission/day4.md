### 코드 리팩토링 해보기

- 이전 코드
```
public boolean validateOrder(Order order) {
    if (order.getItems().size() == 0) {
        log.info(""주문 항목이 없습니다."");
        return false;
    } else {
        if (order.getTotalPrice() > 0) {
            if (!order.hasCustomerInfo()) {
                log.info(""사용자 정보가 없습니다."");
                return false;
            } else {
                return true;
            }
        } else if (!(order.getTotalPrice() > 0)) {
            log.info(""올바르지 않은 총 가격입니다."");
            return false;
        }
    }
    return true;
}
```

</br>

- 리팩토링 후
```
public boolean validate(Order order) {
    if (order.isEmpty) {
        log.info("주문 항목이 없습니다.");
        return false;
    }

    if (order.isInValidPrice) {
        log.info("올바르지 않은 총 가격입니다.");
        return false;
    }

    if (order.isInValidCustomerInfo) {
        log.info("사용자 정보가 없습니다.");
        return false;
    }

    return true;
}
```

</br>

### 객체지향 원칙 직관적인 나만의 단어로 다시 정리해보기

**SRP**
- 클래스는 하나의 변경 이유만을 가져야 하며, 하나의 책임만을 수행한다.
- 만약 출력에 대한 요구사항이 변경된다면, 출력에 대한 책임을 갖은 클래스 만을 변경하면 되도록 한다는 것.
- 메서드를 통해서 메시지를 전하여 객체끼리 협력한다.

**OCP**
- 클라이언트 코드의 변경 없이 기능을 확장할 수 있어야 한다. 인터페이스 활용, 추상화를 통한 관계 구축

**LSP**
- 부모 타입으로 캐스팅 하여 메서드를 실행해도 의도된 동작이 실행되도록 해야 한다

**ISP**
- 클라이언트에게 꼭 필요한 인터페이스만을 제공하도록 한다.
- 클라이언트에 불필요한 메서드를 포함하여 인터페이스를 제공한다면, 인터페이스를 나눈다

**DIP**
- 클래스에 의존하려 할 때 구현체에 의존하지 않고 추상화된 인터페이스에 의존하도록 한다
