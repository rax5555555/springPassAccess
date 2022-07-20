package rax.springpassaccess.forms;

import lombok.Data;

@Data
public class UserForm {
    private Long id;
    private String firstName;
    private String email;
    private String password;
}
