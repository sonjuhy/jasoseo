package com.intergrated.jasoseo.api.controller;

import com.intergrated.jasoseo.api.dto.ContentDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @GetMapping("/getAllContent/{annPk}")
    public List<ContentDto> getAllContent(@PathVariable int annPk){
        List<ContentDto> list = new ArrayList<>();
        return list;
    }

    @GetMapping("/getContentByNum/{annPk}/{num}")
    public ContentDto getContentByNum(@PathVariable int annPk, @PathVariable int num){
        ContentDto dto = new ContentDto();
        return dto;
    }

    @PostMapping("/setAllContent")
    public void setAllContent(@RequestBody List<ContentDto> list){

    }

    @PostMapping("/setContentByNum")
    public void setContentByNum(@RequestBody ContentDto dto){

    }

    @PutMapping("/updateAllContent")
    public void updateAllContent(@RequestBody List<ContentDto> list){

    }

    @PutMapping("/updateContentByNum")
    public void updateContentByNum(@RequestBody ContentDto dto){

    }
    
    @DeleteMapping("/deleteAllContent/{annPk}")
    public void deleteAllContent(@PathVariable int annPk){
        
    }
    @DeleteMapping("/deleteContentByNum/{annPk}/{num}")
    public void deleteContentByNum(@PathVariable int annPk, @PathVariable int num){

    }
}
