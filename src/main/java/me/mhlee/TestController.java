package me.mhlee;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {
    @GetMapping("/hi")
    public String hi() {
        return "안녕하세요! 에 대한 응답 리턴입니다.";
    }
    @GetMapping("/test")
    public String test() {
        return "안녕하세요! 에 대한 테스트 응답입니다.";
    }
    @PostMapping("/test")
    public String posttest() {
        return "안녕하세요! post&test에 대한 응답입니다.";
    }
    @PutMapping("/test")
    public String puttest() {
        return "안녕하세요! put&test에 대한 응답입니다.";
    }
    @DeleteMapping("/test")
    public String deleteTest() {
        return "안녕하세요! delete&test에 대한 응답입니다.";
    }
//    @GetMapping("/member")
//    public String getALLMember() { return ""; }
}