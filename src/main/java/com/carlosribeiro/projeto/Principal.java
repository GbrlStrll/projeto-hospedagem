package com.carlosribeiro.projeto;

import com.carlosribeiro.projeto.dao.*;
import com.carlosribeiro.projeto.model.*;
import com.carlosribeiro.projeto.util.FabricaDeObjetos;
import corejava.Console;

import java.io.*;
import java.util.Map;

public class Principal {

    private static PrincipalCliente principalCliente = FabricaDeObjetos.getInstance(PrincipalCliente.class);

    private static PrincipalAcomodacao principalAcomodacao = FabricaDeObjetos.getInstance(PrincipalAcomodacao.class);

    private static PrincipalProduto principalProduto = FabricaDeObjetos.getInstance(PrincipalProduto.class);

    public static void main(String[] args) {

        recuperarDados();

        boolean continua = true;
        while (continua) {
            System.out.println('\n' + "========================================================");
            System.out.println('\n' + "O que você deseja fazer?");
            System.out.println('\n' + "1. Tratar Clientes");
            System.out.println("2. Tratar Acomodações");
            System.out.println("3. Tratar Produtos");
            System.out.println("4. Sair");

            int opcao = Console.readInt('\n' + "Digite um número entre 1 e 4:");

            System.out.println();

            switch (opcao) {
                case 1 -> {
                    principalCliente.principal();
                }
                case 2 -> {
                    principalAcomodacao.principal();
                }
                case 3 -> {
                    principalProduto.principal();
                }
                case 4 -> {
                    salvarDados();
                    continua = false;
                }

                default -> System.out.println('\n' + "Opção inválida!");
            }
        }
    }

    private static void recuperarDados() {
        ClienteDao clienteDao = FabricaDeObjetos.getInstance(ClienteDao.class);
        AcomodacaoDao acomodacaoDao = FabricaDeObjetos.getInstance(AcomodacaoDao.class);
        ProdutoDao produtoDao = FabricaDeObjetos.getInstance(ProdutoDao.class);

        try {

            FileInputStream fis = new FileInputStream("dados.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Map<Integer, Cliente> mapDeClientes = (Map<Integer, Cliente>) ois.readObject();
            int contadorCliente = ois.readInt();
            clienteDao.setMap(mapDeClientes);
            clienteDao.setContador(contadorCliente);

            Map<Integer, Acomodacao> mapDeAcomodacoes = (Map<Integer, Acomodacao>) ois.readObject();
            int contadorAcomodacao = ois.readInt();
            acomodacaoDao.setMap(mapDeAcomodacoes);
            acomodacaoDao.setContador(contadorAcomodacao);

            Map<Integer, Produto> mapDeProdutos = (Map<Integer, Produto>) ois.readObject();
            int contadorProduto = ois.readInt();
            produtoDao.setMap(mapDeProdutos);
            produtoDao.setContador(contadorProduto);
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo dados.dat não encontrado. Será criado.");
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void salvarDados() {
        ClienteDao clienteDao = FabricaDeObjetos.getInstance(ClienteDao.class);
        Map<Integer, Cliente> mapDeClientes = clienteDao.getMap();
        int contadorCliente = clienteDao.getContador();

        AcomodacaoDao acomodacaoDao = FabricaDeObjetos.getInstance(AcomodacaoDao.class);
        Map<Integer, Acomodacao> mapDeAcomodacoes = acomodacaoDao.getMap();
        int contadorAcomodacao = acomodacaoDao.getContador();

        ProdutoDao produtoDao = FabricaDeObjetos.getInstance(ProdutoDao.class);
        Map<Integer, Produto> mapDeProdutos = produtoDao.getMap();
        int contadorProduto = produtoDao.getContador();

        try {
            FileOutputStream fos = new FileOutputStream("dados.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mapDeClientes);
            oos.writeInt(contadorCliente);
            oos.writeObject(mapDeAcomodacoes);
            oos.writeInt(contadorAcomodacao);
            oos.writeObject(mapDeProdutos);
            oos.writeInt(contadorProduto);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}