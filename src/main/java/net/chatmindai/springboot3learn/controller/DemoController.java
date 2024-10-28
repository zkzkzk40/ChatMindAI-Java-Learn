package net.chatmindai.springboot3learn.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.chatmindai.springboot3learn.annonation.LogInfo;
import net.chatmindai.springboot3learn.entity.demo.dto.DemoDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Slf4j
@Tag(name = "演示用的控制器", description = "演示用的控制器")
@RestController
@RequestMapping("/demo")
public class DemoController {

    /**
     * 处理GET请求的方法
     * @GetMapping是@RequestMapping(method = RequestMethod.GET)的简写
     * 其他HTTP方法还有:
     * @PostMapping - 处理POST请求
     * @PutMapping - 处理PUT请求
     * @DeleteMapping - 处理DELETE请求
     * @PatchMapping - 处理PATCH请求
     */
    @Operation(summary = "返回一个简单的json")
    @GetMapping("/hello")
    public Object hello(){
        // 创建一个Map对象并初始化,使用Java 9引入的Map.of()方法
        Map<String,Object> map = new java.util.HashMap<>(Map.of("name", "chatmindai", "age", 18));

        // 向map中添加新的键值对
        map.put("introduction","we are chatmindai");

        // 直接返回map对象,Spring会自动将其转换为JSON格式
        // 如果想要更细粒度的控制,可以考虑使用ResponseEntity<>
        return map;
    }
    @LogInfo("获取用户信息")
    @Operation(summary = "使用DemoDTO对象")
    @PostMapping("/demo2")
    public DemoDTO useDemoDTO(@Validated @RequestBody DemoDTO demoDTO) {
        log.info("入参为: {}", demoDTO);
        return demoDTO;
    }
    // 可以添加更多的方法来处理不同的请求
    // 例如:
    // @PostMapping("/create")
    // public ResponseEntity<?> createSomething(@RequestBody SomeDTO dto) { ... }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<?> updateSomething(@PathVariable Long id, @RequestBody SomeDTO dto) { ... }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<?> deleteSomething(@PathVariable Long id) { ... }
}
