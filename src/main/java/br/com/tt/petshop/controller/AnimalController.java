package br.com.tt.petshop.controller;

import br.com.tt.petshop.model.Animal;
import br.com.tt.petshop.repository.AnimalRepository;
import br.com.tt.petshop.service.AnimalService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/animais-listar")
public class AnimalController {
    private final AnimalService animalService;


    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping()
    public ModelAndView listar(@RequestParam Long clientId){
        List<Animal> animais = animalService.listar(clientId);
        ModelAndView mv = new ModelAndView("animais-listar");
        mv.addObject("animais", animais);
        mv.addObject("clientId", animais.get(0).getClientId());
        Animal animal = new Animal();
        animal.setClientId(animais.get(0).getClientId());
        mv.addObject("animal", animal);

        return mv ;
    }

    @PostMapping()
    public String adicionar(Animal animal){
        System.out.println(animal.toString());
        animalService.adicionar(animal);
        return "redirect:/animais-listar?clientId="+animal.getClientId();
    }
}
