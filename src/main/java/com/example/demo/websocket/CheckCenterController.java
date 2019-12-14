package com.example.demo.websocket;

import com.example.demo.utils.R;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    //页面请求
    @GetMapping("/socket/{cid}")
    public ModelAndView socket(@PathVariable String cid) {
        ModelAndView mav = new ModelAndView("/websocket");
        mav.addObject("cid", cid);
        return mav;
    }

    //推送数据接口
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
