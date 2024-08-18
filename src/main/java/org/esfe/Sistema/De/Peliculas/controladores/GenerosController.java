package org.esfe.Sistema.De.Peliculas.controladores;

import org.esfe.Sistema.De.Peliculas.Entidades.Generos;
import org.esfe.Sistema.De.Peliculas.services.interfaces.IGenerosServices;
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
@RequestMapping("/generos")
public class GenerosController {

    @Autowired
    private IGenerosServices generosServices;

    @GetMapping

    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional <Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Generos> generos = generosServices.buscarTodosPaginados(pageable);
        model.addAttribute("generos", generos);

        int totalPagees = generos.getTotalPages();
        if(totalPagees > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(1, totalPagees)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pagesNumbers);
        }

        return "generos/index";
    }

    @GetMapping("/create")
    public String create(Generos generos){
        return "generos/create";
    }

    @PostMapping("/save")
    public String save(Generos generos, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(generos);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "generos/create";
        }

        generosServices.createOEditar(generos);
        attributes.addFlashAttribute("msg", "Genero creado correctamente");
        return "redirect:/generos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Generos generos = generosServices.buscarPorId(id).get();
        model.addAttribute("generos", generos);
        return "generos/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Generos generos = generosServices.buscarPorId(id).get();
        model.addAttribute("generos", generos);
        return "generos/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Generos generos = generosServices.buscarPorId(id).get();
        model.addAttribute("generos", generos);
        return "generos/delete";
    }

    @PostMapping("/delete")
    public String delete(Generos generos, RedirectAttributes attributes){
        generosServices.eliminarPorId(generos.getId());
        attributes.addFlashAttribute("msg", "Genero eliminado correctamente");
        return "redirect:/generos";
    }
}
