package br.com.tt.petshop.controller;

import br.com.tt.petshop.exeption.ClienteExeption;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ClienteController {

    //@Autowired
    //private ClienteService clienteService;
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("sistema", "PetShop");

        List<Cliente> clientes = clienteService.listar();
        model.addAttribute("clientes",clientes);

        return "index";
    }

    @GetMapping("/cliente-adicionar")
    public String paginaAdicionar(Model model){
        return "cliente-adicionar";
    }

    @PostMapping("/cliente-form")
    public ModelAndView clienteForm(Cliente novoCliente, ModelAndView mv){
        mv = new ModelAndView("cliente-form");
        try{
            clienteService.adicionar(novoCliente);
            return new ModelAndView(String.format("redirect:/"));
        }catch (ClienteExeption e){
            mv.addObject("erro", e.getMessage());
            return mv;
        }


    }

    @GetMapping("/cliente-excluir")
    public RedirectView excluirCliente(@RequestParam Long id){
        clienteService.remover(id);
        return new RedirectView("/");
    }

}
