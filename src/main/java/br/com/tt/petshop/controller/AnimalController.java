package br.com.tt.petshop.controller;

import br.com.tt.petshop.exeption.AnimalExeption;
import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.model.Cliente;
import br.com.tt.petshop.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller

public class AnimalController {
    private final AnimalService animalService;


    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/animais-listar")
    public ModelAndView listar(@RequestParam Long cliente){
        ModelAndView mv = new ModelAndView("animais-listar");

        addAttributesToModel(cliente, mv);

        return mv ;
    }

    private void addAttributesToModel(Long cliente, ModelAndView mv) {
        List<Animal> animais = null;
        try {
             animais = animalService.listar(cliente);

        }catch(AnimalExeption e){
            animais = new ArrayList<>();
        }finally {
            mv.addObject("animais", animais);
            mv.addObject("clientId", cliente);
            Animal animal = new Animal();
            animal.setCliente(new Cliente());
            animal.getCliente().setId(cliente);
            mv.addObject("animal", animal);
            List<String> especies = animalService.listarEspecies();
            mv.addObject("especies", especies);
        }
    }

    @PostMapping("/animais-listar")
    public ModelAndView adicionar(Animal animal, ModelAndView mv){
        System.out.println(animal.toString());
        try {
            animalService.adicionar(animal);
            return new ModelAndView(String.format("redirect:/animais-listar?cliente=%d", animal.getCliente().getId()));
        }catch (AnimalExeption e){
            mv.addObject("erro", e.getMessage());
            addAttributesToModel(animal.getCliente().getId(), mv);

            return mv;
        }
    }

    @GetMapping("/animal-excluir")
    public RedirectView excluirAnimal(@RequestParam Long id){
        animalService.remover(id);
        return new RedirectView("/animais-listar");
    }
}
