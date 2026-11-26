package com.carlosribeiro.projeto;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.model.Produto;
import com.carlosribeiro.projeto.service.ProdutoService;
import com.carlosribeiro.projeto.util.Util;
import corejava.Console;

import java.util.List;

public class PrincipalProduto
{
    @Autowired
    private ProdutoService produtoService;

	public void principal() {

		String nome;
		double preco;
		Produto umProduto;

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "========================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um produto");
			System.out.println("2. Alterar um produto");
			System.out.println("3. Remover um produto");
			System.out.println("4. Listar relação de produtos");
            System.out.println("5. Listar produtos ordenados por nome");
            System.out.println("6. Listar produtos ordenados por nome descendentemente");
			System.out.println("7. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 7:");

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine('\n' + "Digite o nome do produto: ");
                    preco = Console.readDouble("Digite o preço do produto: ");

                    Produto produto = new Produto(nome, preco);
                    produtoService.cadastrar(produto);

                    System.out.println("Produto cadastrado com sucesso!");
                }
                case 2 ->
                {
                    int numero = Console.readInt('\n' +
                            "Digite o número do produto que você deseja alterar: ");
                    try {
                        umProduto = produtoService.recuperar(numero);

                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Nome");
                        System.out.println("2. Preço");

                        int opcaoAlteracao = Console.readInt('\n' + "Digite 1 ou 2:");

                        switch (opcaoAlteracao) {
                            case 1 -> {
                                String novoNome = Console.readLine("Digite o novo nome: ");
                                umProduto.setNome(novoNome);
                                System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
                            }
                            case 2 -> {
                                double novoPreco = Console.readDouble("Digite o novo preço: ");
                                umProduto.setPreco(novoPreco);
                                System.out.println('\n' + "Alteração de preço efetuada com sucesso!");
                            }
                            default -> System.out.println('\n' + "Opção inválida!");
                        }
                    }catch(EntidadeNaoEncontradaException e){
                            System.out.println(e.getMessage());
                        }
                }
                case 3 ->
                {
                    int numero = Console.readInt('\n' +
                            "Digite o número do produto que você deseja remover: ");

                    try {
                        produtoService.remover(numero);
                        System.out.println("Produto removido com sucesso");
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    if (produtoService.vazio())
                        System.out.println('\n' + "Não há produtos cadastrados.");
                    else {
                        for (Produto produto : produtoService.recuperarTodos()) {
                            System.out.println(produto);
                        }
                    }
                }
                case 5 -> {

                    if (produtoService.vazio())
                        System.out.println('\n' + "Não há produtos cadastrados.");
                    else {
                        List<Produto> produtos = produtoService.recuperarProdutosOrdenadosPorNome();
                        for (Produto produto : produtos) {
                            System.out.println("Número = " + produto.getNumero() +
                                    "   Nome = " + produto.getNome() +
                                    "   Preço = " + Util.formatarNumero(produto.getPreco()));
                        }
                    }
                }
                case 6 -> {

                    if (produtoService.vazio())
                        System.out.println('\n' + "Não há produtos na lista.");
                    else {
                        List<Produto> produtos = produtoService.recuperarProdutosOrdenadosDescendentementePorNome();
                        for (Produto produto : produtos) {
                            System.out.println("Número = " + produto.getNumero() +
                                    "   Nome = " + produto.getNome() +
                                    "   Preço = " + Util.formatarMoeda(produto.getPreco()));
                        }
                    }
                }
                case 7 ->
                        continua = false;
                default -> System.out.println('\n' + "Opção inválida!");
            }
		}
	}
}