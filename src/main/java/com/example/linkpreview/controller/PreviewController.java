package com.example.linkpreview.controller;

import com.example.linkpreview.Entity.Link;

import com.example.linkpreview.Entity.Userd;
import com.example.linkpreview.service.LinkService;
import com.example.linkpreview.service.UserdService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@RestController
public class PreviewController {

    private final Logger logger= LogManager.getLogger(getClass());

    @Autowired
    private LinkService linkService;
    @Autowired
    private UserdService userdService;


    /*
    * Function to check if endpoints are working
    */
    @GetMapping(value = "/hello")
    public String hello(){
        logger.info("Generating Hello Message!");
        return "Hello!";
    }

/*
* Home Page of the application
* index.jsp file is displayed
*/
    @GetMapping({ "/home"})
    public ModelAndView home() {
        logger.info("Entering home page");
        ModelAndView model = new ModelAndView("index");

        return model;
    }

    /*
    * API end point which gives us the preview of the link in view
     */
    @GetMapping("/preview/view")
    public ModelAndView linkPreviewInView(@RequestParam(value = "url", required = true) String url) {

        ModelAndView model = new ModelAndView("link");
        logger.info("Entered preview ");

        try {
            Link link= linkService.extractData(url);
            linkService.addLink(link);
            logger.info("Extracted url= "+link);
            model.addObject("link", link);
        }

        catch (Exception e) {
            logger.error("Unable to connect to ", url);
            model.addObject("css", "danger");
            model.addObject("msg", "Unable to connect to '" + url + "': " + e.getMessage());
        }
        return model;
    }

    /*
    This function gives us the data extracted from the url in json format
     */
    @GetMapping("/preview/api")
    public Link linkPreviewInJson(@RequestParam(value = "url", required = true) String url) {
        Link link = null;
        try {
            link = linkService.extractData(url);
        }catch (IOException ioException){
            logger.error("IO exception error: "+ioException.getMessage());
        }
        return link;
    }



    @RequestMapping(value = "/user/add/{userName}/{password}")
    public void register(@PathVariable String userName, @PathVariable String password){
        Userd userd=new Userd(userName,password,"user",true);
        userdService.addUser(userd);
    }

    @RequestMapping(value = "/user/show/{username}")
    public Userd showUserByName(@PathVariable String username){
        return userdService.getUserd(username);
    }

    @GetMapping(value = "/user/hello")
    public String helo(){
        return "Hello from User Controller";
    }


}
