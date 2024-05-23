package com.intergrated.jasoseo.api.controller;

import com.intergrated.jasoseo.api.dto.AnnouncementDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ann")
public class AnnController {
    @GetMapping("/getAllAnn/{userPk}")
    public List<AnnouncementDto> getAllAnn(@PathVariable int userPk){
        List<AnnouncementDto> list =  new ArrayList<>();
        return list;
    }
    
    @GetMapping("/getAnnByNum/{userPk}/{num}")
    public AnnouncementDto getAnnByNum(@PathVariable int userPk, @PathVariable int num){
        AnnouncementDto dto = new AnnouncementDto();
        return dto;
    }


    @PostMapping("/setAllAnn")
    public void setAllAnn(@RequestBody List<AnnouncementDto> list){

    }

    @PostMapping("/setAnnByNum")
    public void setAnnByNum(@RequestBody AnnouncementDto dto){

    }

    @PutMapping("/updateAllAnn")
    public void updateAllAnn(@RequestBody List<AnnouncementDto> list){

    }

    @PutMapping("/updateAnnByNum")
    public void updateAnnByNum(@RequestBody AnnouncementDto dto){

    }

    @DeleteMapping("/deleteAllAnn/{userPk}")
    public void deleteAllAnn(@PathVariable int userPk){

    }
    @DeleteMapping("/deleteAnnByNum/{userPk}/{num}")
    public void deleteAnnByNum(@PathVariable int userPk, @PathVariable int num){

    }
}
