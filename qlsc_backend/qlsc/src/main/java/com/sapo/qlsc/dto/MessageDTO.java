package com.sapo.qlsc.dto;

import com.sapo.qlsc.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO extends BaseDTO{

    private String title;

    private String content;

    private String url;

    private byte status;

    private UserDTO user;

}
