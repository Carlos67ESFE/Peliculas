package org.esfe.Sistema.De.Peliculas.controladores;

import org.esfe.Sistema.De.Peliculas.Entidades.Actores;
import org.esfe.Sistema.De.Peliculas.Entidades.Generos;
import org.esfe.Sistema.De.Peliculas.services.interfaces.IActoresServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/actores")
public class ActoresController {
    @Autowired
    IActoresServices actoresServices;

    @GetMapping

    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional <Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Actores> actores = actoresServices.buscarTodosPaginados(pageable);
        model.addAttribute("actores", actores);

        int totalPagees = actores.getTotalPages();
        if(totalPagees > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(1, totalPagees)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pagesNumbers);
        }

        return "actores/index";
    }

    @GetMapping("/create")
    public String create(Actores actores){
        return "actores/create";
    }

    @PostMapping("/save")
    public String save(Actores actores, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(actores);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "actores/create";
        }

        actoresServices.createOEditar(actores);
        attributes.addFlashAttribute("msg", "El actor ya está añadido correctamente");
        return "redirect:/actores";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Actores actores = actoresServices.buscarPorId(id).get();
        model.addAttribute("actores", actores);
        return "actores/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Actores actores = actoresServices.buscarPorId(id).get();
        model.addAttribute("actores", actores);
        return "actores/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Actores actores = actoresServices.buscarPorId(id).get();
        model.addAttribute("actores", actores);
        return "actores/delete";
    }

    @PostMapping("/delete")
    public String delete(Actores actores, RedirectAttributes attributes){
        actoresServices.eliminarPorId(actores.getId());
        attributes.addFlashAttribute("msg", "El actor fue eliminado correctamente");
        return "redirect:/actores";
    }
}
