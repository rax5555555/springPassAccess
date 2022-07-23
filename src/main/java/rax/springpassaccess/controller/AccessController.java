package rax.springpassaccess.controller;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import rax.springpassaccess.forms.UidForm;
import rax.springpassaccess.forms.UserForm;
import rax.springpassaccess.models.UidList;
import rax.springpassaccess.uart.UidRepositoryUart;
import rax.springpassaccess.uart.UsersRepositoryJdbcTemplateImpl;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class AccessController {

    DataSource dataSource = new DriverManagerDataSource("jdbc:postgresql://localhost:5432/pcs_2",
            "postgres", "1234");

    UidRepositoryUart usersRepository = new UsersRepositoryJdbcTemplateImpl(dataSource);

    @GetMapping("/all")
    public String getAllUid(Model model) {
        List<UidList> allUid = usersRepository.findAll();
        model.addAttribute("uid", allUid);
        return "showUid";
    }

    @GetMapping("/all/admin")
    public String getAdminConsole(Model model) {
        List<UidList> allUid = usersRepository.findAll();
        model.addAttribute("uid", allUid);
        return "adminConsole";
    }

    @PostMapping("/all/admin")
    public String addUid(UidForm uidForm) {
        usersRepository.addUser(uidForm);
        return "redirect:/all/admin";
    }

    @PostMapping("/all/admin/{allUid-id}/delete")
    public String deleteUid(@PathVariable("allUid-id") Long uidlistId) {
        usersRepository.deleteUser(uidlistId);
        return "redirect:/all/admin";
    }
}
