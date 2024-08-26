package org.esfe.Sistema.De.Peliculas.controladores;


import org.esfe.Sistema.De.Peliculas.Entidades.Directores;
import org.esfe.Sistema.De.Peliculas.services.interfaces.IDirectoresServices;
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
@RequestMapping("/directores")
public class DirectoresController {

    @Autowired
    IDirectoresServices directoresServices;

    @GetMapping

    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size")Optional <Integer> size){
        int currentPage = page.orElse(1) - 1; // si no está seteado se asigna 0
        int pageSize = size.orElse(5); // tamaño de la página, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Directores> directores = directoresServices.buscarTodosPaginados(pageable);
        model.addAttribute("directores", directores);

        int totalPagees = directores.getTotalPages();
        if(totalPagees > 0) {
            List<Integer> pagesNumbers = IntStream.rangeClosed(1, totalPagees)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pagesNumbers);
        }

        return "directores/index";
    }

    @GetMapping("/create")
    public String create(Directores directores){
        return "directores/create";
    }

    @PostMapping("/save")
    public String save(Directores directores, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(directores);
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error.");
            return "directores/create";
        }

        directoresServices.createOEditar(directores);
        attributes.addFlashAttribute("msg", "El director ya está añadido correctamente");
        return "redirect:/directores";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id") Integer id, Model model){
        Directores directores = directoresServices.buscarPorId(id).get();
        model.addAttribute("directores", directores);
        return "directores/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model){
        Directores directores = directoresServices.buscarPorId(id).get();
        model.addAttribute("directores", directores);
        return "directores/edit";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id") Integer id, Model model){
        Directores directores = directoresServices.buscarPorId(id).get();
        model.addAttribute("directores", directores);
        return "directores/delete";
    }

    @PostMapping("/delete")
    public String delete(Directores directores, RedirectAttributes attributes){
        directoresServices.eliminarPorId(directores.getId());
        attributes.addFlashAttribute("msg", "El director fue eliminado correctamente");
        return "redirect:/directores";
    }
}
