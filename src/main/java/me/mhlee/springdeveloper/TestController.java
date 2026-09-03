package me.mhlee.springdeveloper;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/hi")
    public String hi() {
        return "안녕하세요. /hi에 대한 응답입니다.";
    }

    @GetMapping("/test")
    public String test() {
        return "안녕하세요. /test에 대한 응답입니다.";
    }

    @PostMapping("/test")
    public String posttest() {
        return "안녕하세요. /test POST 요청에 대한 응답입니다.";
    }

    @PutMapping("/test")
    public String puttest() {
        return "안녕하세요. /test PUT 요청에 대한 응답입니다.";
    }

    @DeleteMapping("/test")
    public String deletetest() {
        return "안녕하세요. /test DEL 요청에 대한 응답입니다.";
    }





}