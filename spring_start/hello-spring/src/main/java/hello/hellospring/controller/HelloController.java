package hello.hellospring.controller;

import hello.hellospring.HelloSpringApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// Controller: 내부적인 로직을 처리
@Controller
public class HelloController {

    // Web에서 hello라고 입력되면 이 메서드를 호출
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // MVC
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPI(@RequestParam("name") String name, @RequestParam("age") String age) {
        Hello hello = new Hello();
        hello.setName(name);
        hello.setAge(age);
        return hello;
    }

    static class Hello {

        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }

}
