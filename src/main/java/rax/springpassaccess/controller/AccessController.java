package rax.springpassaccess.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import rax.springpassaccess.models.UidList;
import rax.springpassaccess.repositories.UidRepository;

import java.util.List;

@Controller
public class AccessController {

    //private final UidRepository usersRepository;

    //@Autowired
    //public AccessController(UidRepository usersRepository) {
        //this.usersRepository = usersRepository;
    //}

    @GetMapping("/all")
    public String getAllMenu(Model model) {
        //List<UidList> allUid = usersRepository.findAll();
        //model.addAttribute("allUid", allUid);
        return "showMenu";
    }
}
