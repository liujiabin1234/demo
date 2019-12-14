package com.example.demo.websocket;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息推送
 **/
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {
    @Autowired
    WebSocketServer webSocketServer;

    @RequestMapping(value="/pushVideoListToWeb",method=RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Map<String,Object> pushVideoListToWeb(@RequestBody Map<String,Object> param) {
        Map<String,Object> result =new HashMap<String,Object>();
        try {
//            WebSocketServer.sendInfo("有新客户呼入,sltAccountId:"+CommonUtils.getValue(param, "sltAccountId"));
            result.put("operationResult", true);
        }catch (Exception e) {
            result.put("operationResult", true);
        }
        return result;
    }

    @RequestMapping(value="/starManager")
    @ResponseBody
    public Map<String,Object> starManager() {
        Map<String,Object> result =new HashMap<String,Object>();
        try {
            Session session = SecurityUtils.getSubject().getSession();
            webSocketServer.onOpen((javax.websocket.Session) session);
            result.put("operationResult", true);
        }catch (Exception e) {
            result.put("operationResult", true);
        }
        return result;
    }
}
