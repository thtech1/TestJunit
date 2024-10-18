package com.snack.facade;

import com.snack.applications.ProductApplication;
import com.snack.repositories.ProductRepository;
import com.snack.services.ProductService;
import com.snack.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.lang.model.type.NullType;

import static org.junit.jupiter.api.Assertions.*;

public class ProductFacadeTest {
    private ProductFacade productFacade;
    private ProductApplication productApplication;
    private ProductRepository productRepository;
    private ProductService productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        productService = new ProductService();
        productApplication = new ProductApplication (productRepository, productService);
        productFacade = new ProductFacade( productApplication);
        product1 = new Product(1, "Hot Dog", 8.4f, "C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\hotdog.jpg\\");
        product2 = new Product(2, "coxinha", 10.4f, "C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\coxinha.jpg\\");

        productFacade.append(product1);
        productFacade.append(product2);
    }

    @Test
    public void testarSeListaCompletaDeProdutos() {
        assertNotNull(productFacade.getAll());
    }

    @Test
    public void testarSeProdutoECorretoPeloId() {
        Product product2 = productFacade.getById(2);
        assertEquals("coxinha", product2.getDescription());
        assertEquals(10.4f, product2.getPrice());
        assertEquals("C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\coxinha.jpg\\", product2.getImage());
    }

    @Test
    public void validarVerdadeiroParaIdExistenteEFalsoParaIdInexistente(){
        boolean product1 = productApplication.exists(1);
        assertTrue(product1);
        boolean product2 = productApplication.exists(3);
        assertFalse(product2);
    }

    @Test
    public void verificarAdicaoDeUmNovoProduto(){
        Product product3 = new Product(3, "Macarrao", 7.4f, "C:\\Users\\aluno\\IdeaProjects\\NLayerLanche\\src\\imagem\\macarrao.jpg\\");
        productFacade.append(product3);

        assertTrue(productFacade.exists(3));
    }

    @Test
    public void verificarRemocaoDeProdutoExistentePorIdFornecido() {
        productFacade.remove(2);
        assertEquals(1, productFacade.getAll().size());
    }
}
