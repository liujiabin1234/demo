package com.example.demo.websocket;

import com.example.demo.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 使用:
 * 1.先开启socket链接
 * http://localhost:8008/checkcenter/socket/22
 * http://localhost:8008/checkcenter/socket/20
 * 2.后台发送数据
 * http://localhost:8008/checkcenter/socket/push/22?message=I am 22
 * http://localhost:8008/checkcenter/socket/push/20?message=I am 20
 * 注意:
 * 1.页面关闭或刷新socket链接就断开
 */
@Controller
@RequestMapping("/checkcenter")
public class CheckCenterController {

    //客户端开启websocket会话
    @RequestMapping("/socket/{cid}")
    public String socket(@PathVariable String cid, ModelMap modelMap) {
        modelMap.addAttribute("cid", cid);
        return "websocket";
    }

    //服务端向客户端推送数据
    @ResponseBody
    @RequestMapping("/socket/push/{cid}")
    public R pushToWeb(@PathVariable String cid, String message) {
        try {
            WebSocketServer.sendInfo(message, cid);
        } catch (IOException e) {
            e.printStackTrace();
            return R.error(cid + "#" + e.getMessage());
        }
        return R.ok(cid);
    }

}
