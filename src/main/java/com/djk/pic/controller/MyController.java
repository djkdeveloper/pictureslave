package com.djk.pic.controller;

import com.djk.pic.service.PicDownLoadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * MyControler
 *
 * @author djk
 * @date 2016/5/3
 */
@Controller
public class MyController {

    @Resource
    private PicDownLoadService picDownLoadService;

    @RequestMapping("/test")
    @ResponseBody
    public  String test()
    {
        picDownLoadService.downLoadPic("a");
        return "ok";
    }

}
