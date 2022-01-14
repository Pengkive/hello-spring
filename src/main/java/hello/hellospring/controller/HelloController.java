package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String Hello (Model model){  //key, value 값을 직접 받는 방법
        model.addAttribute("data", "spring!!");
        return "hello";
    }

    @GetMapping("hello-mvc")    //key, value값을 파라미터로 받는 방법
    public String helloMvc(@RequestParam("name") String name, Model model){ //ctrl+p -> 파라미터값에 옵션넣기
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){

        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }
    static class Hello{
        private String name;
        //생성자, 메서드 라이딩, getter,setter 자동완성 단축키 => alt+insert

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}




