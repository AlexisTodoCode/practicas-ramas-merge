package Shujaa.sales_system.Controllers;

import Shujaa.sales_system.Models.DetalleVenta;
import Shujaa.sales_system.Services.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detalleVenta")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVenta> getDetalleVenta(){
        return detalleVentaService.getDetalleVenta();
    }

    @GetMapping("/{id}")
    public Optional<DetalleVenta> getDetalleVentaById(@PathVariable Long id){
        return detalleVentaService.getDetalleVentaById(id);
    }

    @PostMapping
    public DetalleVenta saveDetalleVenta(@RequestBody DetalleVenta detalleVenta){
        return detalleVentaService.saveDetalleVenta(detalleVenta);
    }

    @PutMapping("{id}")
    public DetalleVenta updateDetalleVenta(@RequestBody DetalleVenta detalleVenta, @PathVariable int id){
        detalleVenta.setId(id);
        return detalleVentaService.updateDetalleVenta(detalleVenta);
    }

    @DeleteMapping("/{id}")
    public void deleteDetalleVenta(@PathVariable Long id){
        detalleVentaService.deleteDetalleVenta(id);
    }

}
