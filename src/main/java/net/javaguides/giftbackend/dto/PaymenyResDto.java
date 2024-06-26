package net.javaguides.giftbackend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PaymenyResDto implements Serializable {
    private  String status;
    private String message;
    private String URL;
}
