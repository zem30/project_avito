package com.amr.project.webapp.controller;

import com.amr.project.model.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/order")
public class OrderController {

    @GetMapping
    public String orderPage(HttpServletRequest request) {
        System.out.println(request.getAttribute("test"));
        // нужна страница с формированным заказом и генерацией номера данного заказа для оплаты
        return "order_page";
    }

    @PostMapping("/pay")
    public String payment(@ModelAttribute("order") Order order) {
//        try {
//            Payment payment = service.createPayment(order.getPrice(), order.getCurrency(), order.getMethod(),
//                    order.getIntent(), order.getDescription(), "http://localhost:8888/" + CANCEL_URL,
//                    "http://localhost:8888/" + SUCCESS_URL);
//            for(Links link:payment.getLinks()) {
//                if(link.getRel().equals("approval_url")) {
//                    return "redirect:"+link.getHref();
//                }
//            }
//
//        } catch (RESTException e) {
//
//            e.printStackTrace();
//        }
        return "redirect:https://oplata.qiwi.com/form";
    }
}
