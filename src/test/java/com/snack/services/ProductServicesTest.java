package com.snack.services;

import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class ProductServicesTest {
    private static ProductService productService;

    private Product product1;
    private Product product2;


    @BeforeEach
    public void setup() {
        productService = new ProductService();
        product1 = new Product(1, "HotDog", 10.4f, "C:\\Users\\aluno\\Desktop\\NLayerLanche\\src\\imagem\\hotdog.jpg");
        product2 = new Product(2, "coxinha", 10.4f, "C:\\Users\\aluno\\Desktop\\NLayerLanche\\src\\imagem\\coxinha.jpg");

        productService.save(product1);
        productService.save(product2);
    }

    @Test
    public void validarSeProdutoESalvoComImagem() {
        Product product4 = new Product(4, "macarrão", 10.0f, "C:\\Users\\aluno\\Desktop\\NLayerLanche\\src\\imagem\\macarrao.jpg");

        assertEquals(true, productService.save(product4));
    }

    @Test
    public void testarSeProdutoESalvoSemImagem(){
        Product product5 = new Product(5, "Uva", 5.0f, "C:\\Users\\aluno\\Desktop\\NLayerLanche\\src\\imagem\\goiaba.jpg");
        boolean isSaved = productService.save(product5);
        assertFalse(isSaved);
    }

    @Test
    public void testRemoverProdutoExistente() {
        Path path = Paths.get(productService.getImagePathById(product2.getId()));
        productService.remove(product2.getId());
        assertFalse(Files.exists(path));
    }

    @Test
    public void TestarAtualizarproduto() {
        product1.setDescription("Carne");
        productService.update(product1);
        assertEquals("Carne",product1.getDescription());
    }

    @Test
    public void TesteObterCaminhoImgPorId() {
        Path path = Paths.get(productService.getImagePathById(2));
        assertTrue(Files.exists(path));
    }
}
