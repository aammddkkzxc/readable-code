## 고민사항들

### 검증하는 데이터가 변경된다면 어떻게 대처해야 할까
- SeatPassesTest나 LockerPassesTest 에서 조건에 만족하는 pass들을 찾아오는 find~ 메소드를 테스트 해보았다
- findBy~메소드의 결과인 List<SeatPass> 혹은 LockerPass의 모든 데이터(passtype, duration, price) 이 일치하는지를 검증했다
- 만약 데이터 정책이 바뀐다면 구현한 테스트 코드에서 데이터들도 일일히 바꿔줘야하는 번거로움이 생길 것 으로 예상된다.
- 이런식으로 데이터 하나하나 전부 검증 하는 것은 잘못된 방법일까? 아니면 데이터가 변경될 가능성이 적다고 판단하고 이렇게 구현하는 것이 맞을까
- 아니면 아예 다른 방식으로 테스트 코드를 구현해야 할까

### 테스트를 위해 프로덕션 코드를 바꾸는 기준
- StudyCafeSeatPasses의 findPassBy메소드는 public 메소드 이지만 findPassBy메소드를 호출하는 StudyCafePassMachine의 findPassCandidatesBy, selectPass는 private메소드 이다
- findPassCandidatesBy, selectPass 메소드들은 내부에서 단순히 다른 메소드들을 호출하는 형태를 가져서 테스트의 필요성이 낮다고 느껴졌다
- 따라서 굳이 private를 public으로 바꾸어서 테스트를 하지는 않았다
- 적절한 판단일까? 만약 더욱 복잡한 비지니스 로직을 가지게 된다면 public으로 바꾸어서라도 테스트 해봐야 하는 걸까

### 테스트 하고자 하는 메소드 이전에 일어나는 비지니스 로직이 바뀌지 않는 다는 가정을 한 채로 테스트 하는 걸까
- StudyCafeSeatPasses의 findPassBy메소드 이전 로직 상 null, 혹은 일지하지 않는 타입이 매개변수로 들어올 수 없다. (ioHandler.askPassTypeSelecting() 에서 예외를 던지도록 하였음)
- 이것을 반영하여 테스트 코드를 구성하는 것이 옳을까 아니면 매개변수로 null, 혹은 일치하지 않는 타입이 올 수 있다는 것을 감안하여 구성해야 할까