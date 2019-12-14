package com.example.demo.websocket;

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

    @RequestMapping(value = "/pushVideoListToWeb", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Map<String, Object> pushVideoListToWeb(@RequestBody Map<String, Object> param) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            WebSocketServer.sendInfo("有新客户呼入,sltAccountId:" + "222222222222");
            result.put("operationResult", true);
        } catch (IOException e) {
            result.put("operationResult", true);
        }
        return result;
    }
}
