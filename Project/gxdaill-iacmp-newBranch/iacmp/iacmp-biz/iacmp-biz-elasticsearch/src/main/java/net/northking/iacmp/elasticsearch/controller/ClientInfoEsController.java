package net.northking.iacmp.elasticsearch.controller;

import com.alibaba.fastjson.JSONObject;
import net.northking.iacmp.elasticsearch.domain.ClientInfoType;
import net.northking.iacmp.elasticsearch.service.IClientInfoEsService;
import net.northking.iacmp.elasticsearch.service.impl.ClientInfoEsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Generated;
import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/es/clientInfoEs")
public class ClientInfoEsController {

    @Autowired
    private IClientInfoEsService clientInfoEsService;

    /**
     * 保存客户信息
     */
    @GetMapping("/add")
    @ResponseBody
    public String saveClientInfo() {
        for (int i = 0; i < 100; i++) {
            ClientInfoType clientInfoType = new ClientInfoType();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            clientInfoType.setId(Long.valueOf(i));
            clientInfoType.setBusino(uuid);
            clientInfoType.setCustomerCode("" + i);
            clientInfoType.setCustomerIdCard("" + i);
            clientInfoType.setCustomerName("张si" + i);
            clientInfoType.setIdCardType("" + i);
            clientInfoType.setMetadata(new JSONObject());
            clientInfoEsService.saveClientInfo(clientInfoType);
        }
        return null;
    }

    /**
     * 查询全部客户信息
     */
    @GetMapping("/findAll")
    @ResponseBody
    public String findAll() {
        List<ClientInfoType> list = clientInfoEsService.findAll();
        for (ClientInfoType clientInfoType : list) {
            System.out.println(clientInfoType);
        }
        return null;
    }

    /**
     * 根据ID删除客户信息
     */
    @GetMapping("/deleteById")
    @ResponseBody
    public boolean deleteById() {
        Long id = Long.valueOf("1");
        boolean isDelete = clientInfoEsService.deleteById(id);
        return isDelete;
    }

    /**
     * 根据ID更新客户信息
     */
    @GetMapping("/updateById")
    @ResponseBody
    public String updateById() {
        Long id = Long.valueOf("2");
        ClientInfoType clientInfoType = clientInfoEsService.findById(id);
        clientInfoType.setCustomerName("李四");
        String result = clientInfoEsService.updateById(clientInfoType);
        return null;
    }

    /**
     * 根据身份证号查询客户信息
     */
    @GetMapping("/findByIdCard")
    @ResponseBody
    public String findByIdCard() {
        String idCard = "2";
        ClientInfoType clientInfoType = clientInfoEsService.findByIdCard(idCard);
        System.out.println(clientInfoType);
        return null;
    }

}
