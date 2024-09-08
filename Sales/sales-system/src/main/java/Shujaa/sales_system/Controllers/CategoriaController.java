package Shujaa.sales_system.Controllers;

import Shujaa.sales_system.Models.Categoria;
import Shujaa.sales_system.Services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> getCategorias(){
        return categoriaService.getCategorias();
    }

    @GetMapping("/{id}")
    public Optional<Categoria> getCategoriaById(@PathVariable Long id){
        return categoriaService.getCategoriaById(id);
    }

    @PostMapping
    public Categoria saveProducto(@RequestBody Categoria categoria){
        return categoriaService.saveCategoria(categoria);
    }

    @PutMapping("/{id}")
    public Categoria updateCategoria(@PathVariable int id, @RequestBody Categoria categoria){
        categoria.setId(id);
        return categoriaService.updateCategoria(categoria);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoria(@PathVariable Long id){
        categoriaService.deleteCategoria(id);
    }
}
