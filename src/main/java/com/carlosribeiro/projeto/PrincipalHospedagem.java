package com.carlosribeiro.projeto;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.exception.AcomodacaoIndisponivelException;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.exception.PeriodoDeHospedagemInvalidoException;
import com.carlosribeiro.projeto.model.Acomodacao;
import com.carlosribeiro.projeto.model.Cliente;
import com.carlosribeiro.projeto.model.Hospedagem;
import com.carlosribeiro.projeto.service.AcomodacaoService;
import com.carlosribeiro.projeto.service.ClienteService;
import com.carlosribeiro.projeto.service.HospedagemService;
import com.carlosribeiro.projeto.util.Util;
import corejava.Console;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class PrincipalHospedagem {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AcomodacaoService acomodacaoService;
    @Autowired
    private HospedagemService hospedagemService;

    public void principal() {

        String cpf;
        String dataHoraEntradaPrevista;
        String dataHoraSaidaPrevista;
        Cliente cliente;
        Acomodacao acomodacao;
        Hospedagem umaHospedagem;

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Cadastrar uma hospedagem");
            System.out.println("2. Alterar uma hospedagem");
            System.out.println("3. Remover uma hospedagem");
            System.out.println("4. Listar relação de hospedagens");
            System.out.println("5. Listar hospedagens de um cliente");
            System.out.println("6. Voltar");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 6:");

            switch (opcao) {
                case 1 -> {
                    cpf = Console.readLine("Digite o CPF do cliente: ");
                    dataHoraEntradaPrevista = Console.readLine("Digite a data e hora de entrada prevista (DD/MM/AAAA HH:MM): ");
                    dataHoraSaidaPrevista = Console.readLine("Digite a data e hora de saída prevista (DD/MM/AAAA HH:MM): ");

                    try {
                        cliente = clienteService.recuperarPorCpf(cpf);
                        LocalDateTime entradaPrevista = Util.converterParaDataHora(dataHoraEntradaPrevista);
                        LocalDateTime saidaPrevista = Util.converterParaDataHora(dataHoraSaidaPrevista);
                        List<Acomodacao> acomodacoesDisponiveis = hospedagemService.recuperarAcomodacoesDisponiveis(entradaPrevista, saidaPrevista);
                        if (acomodacoesDisponiveis.isEmpty())
                            System.out.println('\n' + "Não há acomodações disponíveis neste período.");
                        else {
                            System.out.println('\n' + "Acomodações disponíveis:");
                            for (Acomodacao umaAcomodacao : acomodacoesDisponiveis) {
                                System.out.println(umaAcomodacao);
                            }
                            int idAcomodacao = Console.readInt('\n' + "Digite o id da acomodação: ");
                            acomodacao = acomodacaoService.recuperar(idAcomodacao);
                            Hospedagem hospedagem = new Hospedagem(entradaPrevista, saidaPrevista, cliente, acomodacao);
                            umaHospedagem = hospedagemService.cadastrar(hospedagem);
                            System.out.println("Hospedagem com id = " + umaHospedagem.getId() +
                                " cadastrada com sucesso!");
                        }
                    }
                    catch(EntidadeNaoEncontradaException | PeriodoDeHospedagemInvalidoException | AcomodacaoIndisponivelException e) {
                        System.out.println(e.getMessage());
                    }
                    catch(DateTimeParseException e) {
                        System.out.println("Data e hora inválida.");
                    }
                }
                case 2 ->
                {
                    int id = Console.readInt('\n' +
                            "Digite o id da hospedagem que você deseja alterar: ");
                    try {
                        umaHospedagem = hospedagemService.recuperar(id);

                        System.out.println('\n' + "O que você deseja alterar?");
                        System.out.println('\n' + "1. Data e hora de entrada prevista");
                        System.out.println("2. Data e hora de entrada real");
                        System.out.println("3. Data e hora de saída prevista");
                        System.out.println("4. Data e hora de saída real");

                        int opcaoAlteracao = Console.readInt('\n' + "Digite um número entre 1 e 4:");

                        switch (opcaoAlteracao) {
                            case 1 -> {
                                String novaDataHoraEntradaPrevista = Console.readLine("Digite a nova data e hora de entrada prevista (DD/MM/AAAA HH:MM): ");
                                umaHospedagem.setDataHoraEntradaPrevista(Util.converterParaDataHora(novaDataHoraEntradaPrevista));
                                System.out.println('\n' + "Alteração de data e hora de entrada prevista efetuada com sucesso!");
                            }
                            case 2 -> {
                                String novaDataHoraEntradaReal = Console.readLine("Digite a nova data e hora de entrada real (DD/MM/AAAA HH:MM): ");
                                umaHospedagem.setDataHoraEntradaReal(Util.converterParaDataHora(novaDataHoraEntradaReal));
                                System.out.println('\n' + "Alteração de data e hora de entrada real efetuada com sucesso!");
                            }
                            case 3 -> {
                                String novaDataHoraSaidaPrevista = Console.readLine("Digite a nova data e hora de saída prevista (DD/MM/AAAA HH:MM): ");
                                umaHospedagem.setDataHoraSaidaPrevista(Util.converterParaDataHora(novaDataHoraSaidaPrevista));
                                System.out.println('\n' + "Alteração de data e hora de saída prevista efetuada com sucesso!");
                            }
                            case 4 -> {
                                String novaDataHoraSaidaReal = Console.readLine("Digite a nova data e hora de saída real (DD/MM/AAAA HH:MM): ");
                                umaHospedagem.setDataHoraSaidaReal(Util.converterParaDataHora(novaDataHoraSaidaReal));
                                System.out.println('\n' + "Alteração de data e hora de saída real efetuada com sucesso!");
                            }
                            default -> System.out.println('\n' + "Opção inválida!");
                        }
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    } catch (DateTimeParseException e){
                        System.out.println("Data e hora inválida.");
                    }
                }
                case 3 ->
                {
                    int id = Console.readInt('\n' +
                            "Digite o id da hospedagem que você deseja remover: ");

                    try {
                        hospedagemService.remover(id);
                        System.out.println("Hospedagem removida com sucesso");
                    } catch (EntidadeNaoEncontradaException e){
                        System.out.println(e.getMessage());
                    }
                }
                case 4 -> {
                    if (hospedagemService.vazio())
                        System.out.println('\n' + "Não há hospedagens cadastradas.");
                    else {
                        for (Hospedagem hospedagem : hospedagemService.recuperarTodos()) {
                            System.out.println(hospedagem);
                        }
                    }
                }
                case 5 -> {
                    int idCliente = Console.readInt('\n' + "Digite o id do cliente: ");
                    try {
                        List<Hospedagem> hospedagens = hospedagemService.recuperarTodasAsHospedagensDeUmCliente(idCliente);
                        if (hospedagens.isEmpty())
                            System.out.println('\n' + "Não há hospedagens cadastradas para este cliente.");
                        else {
                            for (Hospedagem hospedagem : hospedagens) {
                                System.out.println(hospedagem);
                            }
                        }
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
