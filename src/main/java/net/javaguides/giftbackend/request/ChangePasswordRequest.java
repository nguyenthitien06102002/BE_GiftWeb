package net.javaguides.giftbackend.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ChangePasswordRequest {
    private String currentPassword;
    private String newPassword;
}
