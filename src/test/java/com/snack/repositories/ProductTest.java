package com.snack.repositories;



import com.snack.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javax.lang.model.type.NullType;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    private ProductRepository productRepository;

    private Product product1;
    private Product product2;
    private Product product3;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
        product1 = new Product(1, "Hot Dog", 10.4f, ""); // 0x0003
        product2 = new Product(2, "coxinha", 10.4f, ""); // 0x0005
        product3 = new Product(3, "macarrao", 10.4f, "");// 0x0002

        productRepository.append(product1);
        productRepository.append(product2);
        productRepository.append(product3);
    }

    @Test
    public void VerificarSeOProdutoEAdicionadoCorretamente() {
        Product product4 = new Product(4, "Camarao", 9.9f, "");

        productRepository.append(product4);

        Product productId4 = productRepository.getById(4);
        assertNotNull(productId4);
    }

    @Test
    public void VerificarSeProdutoERecuperadoPeloSeuId(){
        Product productId2 = productRepository.getById(2);
        assertNotNull(productId2);
    }

    @Test
    public void ConfirmarSeProdutoExisteNoRepositorio(){
        assertTrue(productRepository.exists(1));
    }

    @Test
    public void TestarSeProdutoFoiRemovido(){
        productRepository.remove(1);
        assertFalse(productRepository.exists(1));
    }

    @Test
    public void VerificarSeProdutoFoiAtualizado(){
        Product productAAtualizar = new Product(3, "Bacon", 55, "");

        productRepository.update(1, productAAtualizar);

        Product produtoAtualizado = productRepository.getById(1);

        assertEquals(product1, produtoAtualizado);
    }

    @Test
    public void TestaeSeOProdutoFoiArmazenadoFoiRecuperado(){
        productRepository.getAll();
        assertNotNull(productRepository);
    }

    @Test
    public void VerificarComportamentoAoTentarRemoverProdutoInexistente() {

        productRepository.remove(4);
    }


    @Test
        public void testarAtualizacaoDeProdutoInexistente() {
            productRepository.append(product1);

            Product Goiaba = new Product(8, "Goiba ", 10.0f, "");

            Assertions.assertThrows(NoSuchElementException.class, () -> productRepository.update(777, Goiaba));
        }


    @Test
    public void VerificarSeAceitaAdicaoDeProdutosComIdsDuplicados(){
        Product product4 = new Product(4, "Camarao", 9.9f, "");
        Product product5 = new Product(4, "pipoca", 4.9f, "");

        productRepository.append(product4);
        productRepository.append(product5);
        Product productId4 = productRepository.getById(4);
        Product productId5 = productRepository.getById(4);
        assertNotNull(productId4);
        assertNotNull(productId5);
    }

    @Test
    public void ConfirmarSeORepositorioRetornaListaVazia() {
        ProductRepository productRepository = new ProductRepository();

        List<Product> produtos = productRepository.getAll();

        assertTrue(produtos.isEmpty(), "A lista de produtos deve estar vazia ao iniciar.");
    }

}
