package hello.itemservice;

import hello.itemservice.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemServiceApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

//    @Override // @InitBinder를 글로벌 설정으로 하기 위해 만든 메소드, 실무에서 이렇게 쓰는 경우는 거의 없다(주석처리)
//    public Validator getValidator() {
//        return new ItemValidator();
//    }

}
