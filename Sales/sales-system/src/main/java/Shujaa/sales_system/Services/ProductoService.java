package Shujaa.sales_system.Services;

import Shujaa.sales_system.Models.Producto;
import Shujaa.sales_system.Repositorys.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto){
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id){
        return productoRepository.findById(id);
    }

    @Override
    public void deleteProducto(Long id){
        productoRepository.deleteById(id);
    }

}
