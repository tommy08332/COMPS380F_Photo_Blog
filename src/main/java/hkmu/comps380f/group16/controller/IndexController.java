package hkmu.comps380f.group16.controller;

import hkmu.comps380f.group16.dao.PhotosService;
import hkmu.comps380f.group16.exception.PhotoNotFound;
import hkmu.comps380f.group16.model.Photos;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class IndexController {

    @Resource
    private PhotosService photosService;

//    @Resource
//    UsersRepository rep;

    @GetMapping("/")
    public String index(ModelMap model) throws PhotoNotFound, UnsupportedEncodingException {

        List<Photos> photos = photosService.findAllPhotos();

        ArrayList<String> filesArr = new ArrayList<String>();



//        Map<Integer, String> img = new ConcurrentHashMap<Integer, String>();
//
//        Map<Integer, String> filetype = new ConcurrentHashMap<Integer, String>();


        for (Photos photo : photos){

            byte [] imageByteArr = photo.getPhotoData();
            byte [] p = Base64.getEncoder().encode(imageByteArr);
            String photoImg = new String(p, "UTF-8");

            filesArr.add(photoImg);

//            filetype.put(photo.getPhotoId(), photo.getPhotoFileType());

        }

//        System.out.println(img.size());
//        System.out.println(filetype.size());



        model.addAttribute("photos", photos);

        model.addAttribute("fileContent", filesArr);

        return "index";

    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }


}
