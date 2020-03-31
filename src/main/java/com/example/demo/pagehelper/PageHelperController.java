package com.example.demo.pagehelper;

import com.example.demo.mybatis.dto.LargeCount;
import com.example.demo.mybatis.service.LargeCountService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageHelperController {

    @Autowired
    private LargeCountService largeCountService;

    @GetMapping("/getAllLargeCount")
    public String getAllLargeCount(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        PageHelper.startPage(pageNum,5);
        List<LargeCount> list = largeCountService.queryAll();
        PageInfo<LargeCount> pageInfo = new PageInfo<LargeCount>(list);
        model.addAttribute("pageInfo",pageInfo);
        return "pagehelper_list";
    }
}
