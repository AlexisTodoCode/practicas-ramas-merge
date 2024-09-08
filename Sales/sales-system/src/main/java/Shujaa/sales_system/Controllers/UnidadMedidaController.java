package Shujaa.sales_system.Controllers;

import Shujaa.sales_system.Models.UnidadMedida;
import Shujaa.sales_system.Services.UnidadMedidaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/unidadMedida")
public class UnidadMedidaController {
    @Autowired
    UnidadMedidaService unidadMedidaService;

    @GetMapping
    public List<UnidadMedida> getUnidadMedidas(){
        return unidadMedidaService.getUnidadMedidas();
    }

    @GetMapping("/{id}")
    public Optional<UnidadMedida> getUnidadMedidaById(@PathVariable Long id){
        return unidadMedidaService.getUnidadMedidaById(id);
    }

    @PostMapping
    public UnidadMedida saveUnidadMedida(@RequestBody UnidadMedida unidadMedida){
        return unidadMedidaService.saveUnidadMedida(unidadMedida);
    }

    @PutMapping("/{id}")
    public UnidadMedida updateUnidadMedida(@RequestBody UnidadMedida unidadMedida, @PathVariable int id){
        unidadMedida.setId(id);
        return unidadMedidaService.updateUnidadMedida(unidadMedida);
    }

    @DeleteMapping("/{id}")
    public void deleteUnidadMedida(@PathVariable Long id){
        unidadMedidaService.deleteUnidadMedida(id);
    }
}
