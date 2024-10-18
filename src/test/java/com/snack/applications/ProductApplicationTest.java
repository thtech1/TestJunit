package com.snack.applications;

import com.snack.entities.Product;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductApplicationTest {
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication(productRepository, productService);
        product1 = new Product(1, "Hot Dog", 8.4f, "C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\hotdog.jpg");
        product2 = new Product(2, "coxinha", 10.4f, "C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\coxinha.jpg");

        productApplication.append( product1);
        productApplication.append( product2);
    }

    @Test
    public void verificarSeTodosOsProdutosSaoListados() {
        assertNotNull(productApplication.getAll());
    }

    @Test
    public void validarProdutoObtidoPorId(){
        Product product2 = productApplication.getById(2);
        assertEquals("coxinha", product2.getDescription());
        assertEquals(10.4f, product2.getPrice());
        assertEquals("C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\coxinha.jpg", product2.getImage());
    }

    @Test
    public void verificarSeProdutoDaErroSeIdInexistente() {

       assertThrows(NoSuchElementException.class, ()-> {
          productApplication.getById(30);
       });
    }


    @Test
    public void validarSeProdutoExistePorIdValido() {
        boolean product1 = productApplication.exists(2);
        assertTrue(product1);
    }

    @Test
    public void validarSeProdutoInexistentePorIdInvalido() {
        boolean product6 = productApplication.exists(6);
        assertFalse(product6);
    }

    @Test
    public void verificarSeProdutoEImagemAdicionadoESalvoCorretamente() {
        Product product3 = new Product(3, "macarrao", 7.4f, "C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\imagem\\macarrao.jpg");
        productApplication.append(product3);

        assertEquals("macarrao", product3.getDescription());
        assertEquals("C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\imagem\\macarrao.jpg", product3.getImage());
    }

    @Test
    public void verificarRemocaoDoProdutoESuaImagem() {
        Product product = new Product(10, "coxinha", 10.4f, "C:\\Users\\aluno\\Desktop\\NLayerLanche\\src\\imagem\\coxinha.jpg");
        productApplication.append(product);
        productApplication.remove(10);
        assertFalse(productApplication.exists(10));
    }

    @Test
    public void testarSeSistemaContinuaOMesmoAposRemoverProdutoComIdInexistente() {
        assertThrows(NoSuchElementException.class, ()-> {
            productApplication.remove(6);
        });
    }


    @Test
    public void testarAtualizacaoDeProdutoJuntoComSuaImagem(){
        Product produtoNovo = new Product(20, "Bacon", 55, "C:\\Users\\aluno\\Desktop\\NLayerLanche\\src\\imagem\\macarrao.jpg");
        productApplication.append(produtoNovo);

        Product productAAlterar = new Product(20, "Bacon", 55, "C:\\Users\\aluno\\Desktop\\NLayerLanche\\src\\imagem\\macarrao.jpg");
        productApplication.update(20, productAAlterar);

        Product produtoAlterado = productApplication.getById(20);

//        File f = new File("C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\macarrao.jpg\\");

        assertEquals(produtoNovo, produtoAlterado);
//        Assertions.assertFalse(f.exists());
    }

}
