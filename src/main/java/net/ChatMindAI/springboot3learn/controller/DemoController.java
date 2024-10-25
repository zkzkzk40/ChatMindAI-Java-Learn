package net.ChatMindAI.springboot3learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller demo
 * 这是一个演示用的控制器类
 *
 * @author zk
 * @date 2024/10/04
 */
@RestController // 表示这是一个RESTful Web服务的控制器,组合了@Controller和@ResponseBody
@RequestMapping("/demo") // 定义该控制器的基础URL路径
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

    // 可以添加更多的方法来处理不同的请求
    // 例如:
    // @PostMapping("/create")
    // public ResponseEntity<?> createSomething(@RequestBody SomeDTO dto) { ... }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<?> updateSomething(@PathVariable Long id, @RequestBody SomeDTO dto) { ... }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<?> deleteSomething(@PathVariable Long id) { ... }
}

