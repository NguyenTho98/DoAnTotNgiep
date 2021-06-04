package com.doan.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDTO extends BaseDTO {

    private String title;
    private String content;
    private String url;
    private byte status;
    private byte unRead;
    private UserDTO user;

}
