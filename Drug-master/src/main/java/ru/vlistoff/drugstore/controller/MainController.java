package ru.vlistoff.drugstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class MainController {

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/cities")
    public String cities(){
        return "cities";
    }

    @GetMapping("/streets")
    public String streets(){
        return "streets";
    }

    @GetMapping("/producing-country")
    public String producingCountry(){
        return "producing-country";
    }

    @GetMapping("/measure-units")
    public String measureUnits(){
        return "measure-units";
    }

    @GetMapping("/manufacturer")
    public String manufacturer(){
        return "manufacturer";
    }

    @GetMapping("/drugs")
    public String drugs(){
        return "drug";
    }

    @GetMapping("/drug-prices")
    public String drugPrices(){
        return "drug-price";
    }

    @GetMapping("/drugstores")
    public String drugstores(){
        return "drugstores";
    }

    @GetMapping("/requests")
    public String requests(){
        return "requests";
    }

    @GetMapping("purchases")
    public String purchases(){
        return "purchases";
    }

}
