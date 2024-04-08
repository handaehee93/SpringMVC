package mvc.basic.request;

import lombok.Getter;
import lombok.Setter;

// json 데이터가 넘어오면 아래 클래스의 인스턴스로 변환 할 것.
@Getter
@Setter
public class ConverJsonDataToObject {
    private String username;
    private int age;
}
