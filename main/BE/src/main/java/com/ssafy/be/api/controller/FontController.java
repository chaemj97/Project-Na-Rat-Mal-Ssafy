package com.ssafy.be.api.controller;

import com.ssafy.be.api.request.RegistFontReq;
import com.ssafy.be.api.response.CheckFontNameRes;
import com.ssafy.be.api.response.GetFontDetailRes;
import com.ssafy.be.api.response.GetFontsRes;
import com.ssafy.be.api.service.FontService;
import com.ssafy.be.common.auth.UserDetail;
import com.ssafy.be.db.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;




@RestController
@RequestMapping("/api/font")
public class FontController {

    @Autowired
    FontService fontService;

    @GetMapping()
    public ResponseEntity<GetFontsRes> getFonts(@ApiIgnore Authentication auth, Pageable page){
        UserDetail userDetail = (UserDetail) auth.getDetails();
        User user = userDetail.getUser();
        GetFontsRes res = fontService.getFonts(user,page);
        return ResponseEntity.status(200).body(res);
    }
    @GetMapping("/detail/{fontSeq}")
    public ResponseEntity<GetFontDetailRes> getFont(@ApiIgnore Authentication auth, @PathVariable long fontSeq){
        UserDetail userDetail = (UserDetail) auth.getDetails();
        User user =  userDetail.getUser();
        GetFontDetailRes res = fontService.getFont(user,fontSeq);
        return ResponseEntity.status(200).body(res);
    }

    @GetMapping("/checkname/{fontName}")
    public ResponseEntity<CheckFontNameRes> checkFontName(@PathVariable String fontName){
        CheckFontNameRes res = fontService.checkFontName(fontName);
        return ResponseEntity.status(200).body(res);
    }

    @PostMapping()
    public ResponseEntity<Void> registFont(@ApiIgnore Authentication authentication,RegistFontReq req){
        UserDetail userDetail = (UserDetail) authentication.getDetails();
        User user = userDetail.getUser();
        Long fontSeq = fontService.registFontInfo(req.getFontName(),req.getDescription(),user);
        //TODO fastAPI 통신
        return null;
    }
}
