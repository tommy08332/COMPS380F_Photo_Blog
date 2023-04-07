package hkmu.comps380f.group16.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


// For all the photo action

@Controller
@RequestMapping(value = "/photo")
public class PhotoController {

    @GetMapping("/show")
    public String show(){ return "photo"; }

    @GetMapping("/upload")
    public String upload(){ return "upload_photo"; }

    // delete photo

}
