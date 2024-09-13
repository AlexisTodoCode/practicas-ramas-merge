package Shujaa.sales_system.Controllers;

import Shujaa.sales_system.Models.Compra;
import Shujaa.sales_system.Services.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compra")
public class CompraController {
    @Autowired
    private CompraService compraService;

    @GetMapping
    public List<Compra> getCompras(){
        return compraService.getCompras();
    }

    @GetMapping("/{id}")
    public Optional<Compra> getCompraById(@PathVariable Long id){
        return compraService.getCompraById(id);
    }

    @PostMapping
    public Compra saveCompra(@RequestBody Compra compra){
        return compraService.saveCompra(compra);
    }

    @PutMapping("/{id}")
    public Compra updateCompra(@RequestBody Compra compra, @PathVariable int id){
        compra.setId(id);
        return compraService.updateCompra(compra);
    }

    public void deleteCompra(@PathVariable Long id){
        compraService.deleteCompra(id);
    }
}
