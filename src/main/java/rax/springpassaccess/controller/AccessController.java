package rax.springpassaccess.controller;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
