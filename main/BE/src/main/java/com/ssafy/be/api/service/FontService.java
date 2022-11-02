package com.ssafy.be.api.service;


import com.ssafy.be.api.response.CheckFontNameRes;
import com.ssafy.be.api.response.GetFontDetailRes;
import com.ssafy.be.api.response.GetFontsRes;
import com.ssafy.be.db.entity.User;
import org.springframework.data.domain.Pageable;


public interface FontService {
    public GetFontsRes getFonts(User user, Pageable pageable);
    public GetFontDetailRes getFont(User user, Long fontSeq);
    public CheckFontNameRes checkFontName(String fontName);
    public Long registFontInfo(String fontName, String fontDescription, User user);
}
