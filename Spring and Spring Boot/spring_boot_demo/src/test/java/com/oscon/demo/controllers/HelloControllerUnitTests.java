package com.oscon.demo.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;
import org.springframework.validation.support.BindingAwareModelMap;

import static org.junit.Assert.*;

public class HelloControllerUnitTests {
    @Test
    public void testSayHello() throws Exception {
        HelloController controller = new HelloController();
        Model model = new BindingAwareModelMap(); // to test that inner working of Model
        String result = controller.sayHello("World", model);
        assertEquals("World", model.asMap().get("user"));
        assertEquals("hello", result);
    }
}
