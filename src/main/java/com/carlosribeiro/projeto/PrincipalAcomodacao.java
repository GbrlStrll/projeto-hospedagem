package com.carlosribeiro.projeto;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.model.Acomodacao;
import com.carlosribeiro.projeto.service.AcomodacaoService;
import corejava.Console;

public class PrincipalAcomodacao
{
    @Autowired
    private AcomodacaoService acomodacaoService;

	public void principal() {

		int numeroDoQuarto;
		double precoDaDiaria;
		Acomodacao umaAcomodacao;

		boolean continua = true;
		while (continua) {
			System.out.println('\n' + "========================================================");
			System.out.println('\n' + "O que você deseja fazer?");
			System.out.println('\n' + "1. Cadastrar uma acomodação");
			System.out.println("2. Alterar uma acomodação");
			System.out.println("3. Remover uma acomodação");
			System.out.println("4. Listar relação de acomodações");
			System.out.println("5. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 5:");

            switch (opcao) {
                case 1 -> {
                    numeroDoQuarto = Console.readInt('\n' + "Digite o número do quarto: ");
                    precoDaDiaria = Console.readDouble("Digite o preço da diária da acomodação: ");

                    Acomodacao acomodacao = new Acomodacao(numeroDoQuarto, precoDaDiaria);
                    acomodacaoService.cadastrar(acomodacao);

                    System.out.println("Acomodação cadastrada com sucesso!");
                }
                case 2 ->
                {
                    int id = Console.readInt('\n' +
                            "Digite o id da acomodação que você deseja alterar: ");
                    try {
                        umaAcomodacao = acomodacaoService.recuperar(id);

                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Número do quarto");
                        System.out.println("2. Preço da diária");

                        int opcaoAlteracao = Console.readInt('\n' + "Digite 1 ou 2:");

                        switch (opcaoAlteracao) {
                            case 1 -> {
                                int novoNumeroDoQuarto = Console.readInt("Digite o novo número do quarto: ");
                                umaAcomodacao.setNumeroDoQuarto(novoNumeroDoQuarto);
                                System.out.println('\n' + "Alteração de número do quarto efetuada com sucesso!");
                            }
                            case 2 -> {
                                double novoPrecoDaDiaria = Console.readDouble("Digite o novo preço da diária: ");
                                umaAcomodacao.setPrecoDaDiaria(novoPrecoDaDiaria);
                                System.out.println('\n' + "Alteração de preço da diária efetuada com sucesso!");
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
                            "Digite o id da acomodação que você deseja remover: ");

                    try {
                        acomodacaoService.remover(id);
                        System.out.println("Acomodação removida com sucesso");
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    if (acomodacaoService.vazio())
                        System.out.println('\n' + "Não há acomodações cadastradas.");
                    else {
                        for (Acomodacao acomodacao : acomodacaoService.recuperarTodos()) {
                            System.out.println(acomodacao);
                        }
                    }
                }
                case 5 ->
                        continua = false;
                default -> System.out.println('\n' + "Opção inválida!");
            }
		}
	}
}