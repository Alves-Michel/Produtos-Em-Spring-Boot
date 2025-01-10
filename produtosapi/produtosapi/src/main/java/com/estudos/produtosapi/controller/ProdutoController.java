package com.estudos.produtosapi.controller;

import com.estudos.produtosapi.model.Produto;
import com.estudos.produtosapi.repository.ProdutoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
    private ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){

        System.out.println("Produto Recebido = " + produto);

       var id =  UUID.randomUUID().toString();
       produto.setId(id);

        produtoRepository.save(produto);
        return produto;
    }

    @GetMapping("/{id}")
    public Produto obterPorId(@PathVariable("id") String id){

       // Optional<Produto> produto = produtoRepository.findById(id);
       // return produto.isPresent() ? produto.get() : null;
        return produtoRepository.findById(id).orElse(null);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable("id") String id){
        produtoRepository.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable("id") String id, @RequestBody Produto produto) {
        produto.setId(id);
        Produto produtoAtualizado = produtoRepository.save(produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @GetMapping
    public List<Produto> buscar(@RequestParam("nome") String nome){
       return produtoRepository.findByNome(nome);

    }
}