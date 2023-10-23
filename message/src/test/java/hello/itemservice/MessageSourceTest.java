package hello.itemservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    private MessageSource messageSource;

    @Test
    @Order(1)
    @DisplayName("국제화 인사말 메시지 테스트")
    public void helloMessage() {
        String result = messageSource.getMessage("hello", null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @Test
    @Order(2)
    @DisplayName("메시지가 없는 경우 테스트 - 해당 메시지가 없고, 기본 메시지도 없는 경우")
    public void notFoundMessageCode() {
        assertThatThrownBy(() -> messageSource.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    @Order(3)
    @DisplayName("메시지가 없는 경우 테스트 - 해당 메시지가 없을 경우 default message로 대체")
    public void notFoundMessageCodeDefaultMessage() {
        String result = messageSource.getMessage("no_code", null, "기본 메시지", null);
        assertThat(result).isEqualTo("기본 메시지");
    }

    @Test
    @Order(4)
    @DisplayName("추가 매개변수 테스트")
    public void argumentMessage() {
        String result = messageSource.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    /**
     * Locale 정보를 기반으로 국제화 파일을 선택한다.
     * Locale 이 en_US 의 경우, 'messages_en_US ==>  messages_en ==>  messages' 순서로 찾는다.
     * Locale 에 맞추어 구체적인 것이 있으면 구체적인 것을 찾고, 없으면 디폴트를 찾는다고 이해하면 된다
     */
    @Test
    @Order(5)
    @DisplayName("국제화 파일 선택 테스트 - 기본(한국어, messages.properties)")
    public void defaultLanguage(){
        assertThat(messageSource.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
        assertThat(messageSource.getMessage("hello", null, Locale.US)).isEqualTo("hello");
    }

    @Test
    @Order(6)
    @DisplayName("국제화 파일 선택 테스트 - 영어(messages_en.properties)")
    void enLang() {
        assertThat(messageSource.getMessage("hello", null, Locale.ENGLISH)).isEqualTo("hello");
    }

}
