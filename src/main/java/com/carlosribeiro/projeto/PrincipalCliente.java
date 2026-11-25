package com.carlosribeiro.projeto;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.exception.ClienteJaCadastradoException;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.model.Cliente;
import com.carlosribeiro.projeto.service.ClienteService;
import corejava.Console;

public class PrincipalCliente
{
    @Autowired
    private ClienteService clienteService;

	public void principal() {

		String nome;
		String cpf;
		String endereco;
		Cliente umCliente;

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "========================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar um cliente");
			System.out.println("2. Alterar um cliente");
			System.out.println("3. Remover um cliente");
			System.out.println("4. Listar relação de clientes");
			System.out.println("5. Buscar um cliente pelo CPF");
			System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            switch (opcao) {
                case 1 -> {
                    nome = Console.readLine('\n' + "Digite o nome do cliente: ");
                    cpf = Console.readLine("Digite o CPF do cliente: ");
                    endereco = Console.readLine("Digite o endereço do cliente: ");

                    Cliente cliente = new Cliente(nome, cpf, endereco);
                    try {
                        clienteService.cadastrar(cliente);
                        System.out.println("Cliente cadastrado com sucesso!");
                    } catch (ClienteJaCadastradoException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 2 ->
                {
                    int id = Console.readInt('\n' +
                            "Digite o id do cliente que você deseja alterar: ");
                    try {
                        umCliente = clienteService.recuperar(id);

                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Nome");
                        System.out.println("2. Endereço");

                        int opcaoAlteracao = Console.readInt('\n' + "Digite 1 ou 2:");

                        switch (opcaoAlteracao) {
                            case 1 -> {
                                String novoNome = Console.readLine("Digite o novo nome: ");
                                umCliente.setNome(novoNome);
                                System.out.println('\n' + "Alteração de nome efetuada com sucesso!");
                            }
                            case 2 -> {
                                String novoEndereco = Console.readLine("Digite o novo endereço: ");
                                umCliente.setEndereco(novoEndereco);
                                System.out.println('\n' + "Alteração de endereço efetuada com sucesso!");
                            }
                            default -> System.out.println('\n' + "Opção inválida!");
                        }
                    }catch(EntidadeNaoEncontradaException e){
                            System.out.println(e.getMessage());
                        }
                }
                case 3 ->
                {
                    int id = Console.readInt('\n' +
                            "Digite o id do cliente que você deseja remover: ");

                    try {
                        clienteService.remover(id);
                        System.out.println("Cliente removido com sucesso");
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    if (clienteService.vazio())
                        System.out.println('\n' + "Não há clientes cadastrados.");
                    else {
                        for (Cliente cliente : clienteService.recuperarTodos()) {
                            System.out.println(cliente);
                        }
                    }
                }
                case 5 -> {
                    cpf = Console.readLine('\n' + "Digite o CPF do cliente: ");
                    try {
                        umCliente = clienteService.recuperarPorCpf(cpf);
                        System.out.println(umCliente);
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 6 ->
                        continua = false;
                default -> System.out.println('\n' + "Opção inválida!");
            }
		}
	}
}