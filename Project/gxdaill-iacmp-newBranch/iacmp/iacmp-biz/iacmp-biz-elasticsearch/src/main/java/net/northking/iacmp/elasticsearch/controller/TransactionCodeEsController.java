package net.northking.iacmp.elasticsearch.controller;

import net.northking.iacmp.elasticsearch.domain.TransactionCodeType;
import net.northking.iacmp.elasticsearch.service.ITransactionCodeEsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

@Controller
@RequestMapping("/es/transcationCodeEs")
public class TransactionCodeEsController {
    @Autowired
    private ITransactionCodeEsService transactionCodeEsService;

    @GetMapping("/add")
    @ResponseBody
    public TransactionCodeType add() {
        TransactionCodeType transactionCodeType = new TransactionCodeType();
        for (int i = 1; i <= 50; i++) {
            transactionCodeType.setId(Long.valueOf(i));
            String transactionCode = UUID.randomUUID().toString().replaceAll("-", "");
            String busino = UUID.randomUUID().toString().replaceAll("-", "");
            transactionCodeType.setTransactionCode(transactionCode);
            transactionCodeType.setBusino(busino);
            transactionCodeEsService.insertTransactionCode(transactionCodeType);
        }
        return null;
    }

    /**
     * 根据全局流水号查询
     */
    @GetMapping("/findByTransactionCode")
    @ResponseBody
    public TransactionCodeType findByTransactionCode() {
        String transactionCode = "cec82c6b486e4f79a187c356677413f9";
        TransactionCodeType transactionCodeType = transactionCodeEsService.findByTransactionCode(transactionCode);
        System.out.println(transactionCodeType);
        return null;
    }

    /**
     * 根据流水号查询
     */
    @GetMapping("/findByBusino")
    @ResponseBody
    public TransactionCodeType findByBusino() {
        String busino = "073629d1f49e46bf9225d721f9d37cf9";
        TransactionCodeType transactionCodeType = transactionCodeEsService.findByBusino(busino);
        System.out.println(transactionCodeType);
        return null;
    }

    /**
     * 根据全局流水号删除
     */
    @GetMapping("/deleteByTransactionCode")
    @ResponseBody
    public int deleteByTransactionCode() {
        String transcationCode = "cec82c6b486e4f79a187c356677413f9";
        int result = transactionCodeEsService.deleteByTransactionCode(transcationCode);
        return result;
    }
}
