package rider11.hellospringboot.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rider11.hellospringboot.bean.ResponseData;

@RestController
@RequestMapping("/resp")
public class ResponseController {
    @RequestMapping("/base")
    public ResponseData<Date> testBase(){
        return new ResponseData<Date>(new Date());
    }
}
