package net.javaguides.giftbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.javaguides.giftbackend.entity.Products;
import net.javaguides.giftbackend.entity.Users;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddToCartRequest {

    private Users userID;
    private Products product;
    private int quantity;


}
