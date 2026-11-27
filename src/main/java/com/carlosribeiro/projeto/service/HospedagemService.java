package com.carlosribeiro.projeto.service;

import com.carlosribeiro.projeto.annotation.Autowired;
import com.carlosribeiro.projeto.dao.HospedagemDao;
import com.carlosribeiro.projeto.exception.AcomodacaoIndisponivelException;
import com.carlosribeiro.projeto.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.projeto.exception.PeriodoDeHospedagemInvalidoException;
import com.carlosribeiro.projeto.model.Acomodacao;
import com.carlosribeiro.projeto.model.Cliente;
import com.carlosribeiro.projeto.model.Hospedagem;
import com.carlosribeiro.projeto.util.Util;

import java.time.LocalDateTime;
import java.util.List;

public class HospedagemService {

    @Autowired
    private HospedagemDao hospedagemDao;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AcomodacaoService acomodacaoService;

    public Hospedagem cadastrar(Hospedagem hospedagem) {
        validarPeriodo(hospedagem.getDataHoraEntradaPrevista(), hospedagem.getDataHoraSaidaPrevista());
        for (Hospedagem umaHospedagem : hospedagem.getAcomodacao().getHospedagens()) {
            if (periodosSeSobrepoem(umaHospedagem, hospedagem.getDataHoraEntradaPrevista(), hospedagem.getDataHoraSaidaPrevista())) {
                throw new AcomodacaoIndisponivelException(
                    "A acomodação já está reservada de " +
                        Util.formatarDataHora(umaHospedagem.getDataHoraEntradaPrevista()) + " a " +
                        Util.formatarDataHora(recuperarDataHoraSaida(umaHospedagem)));
            }
        }
        hospedagem.setPrecoDaDiaria(hospedagem.getAcomodacao().getPrecoDaDiaria());
        hospedagem.getCliente().getHospedagens().add(hospedagem);
        hospedagem.getAcomodacao().getHospedagens().add(hospedagem);
        return hospedagemDao.cadastrar(hospedagem);
    }

     public Hospedagem remover(int id) {
        Hospedagem hospedagem = recuperar(id);
        hospedagem.getCliente().getHospedagens().remove(hospedagem);
        hospedagem.getAcomodacao().getHospedagens().remove(hospedagem);
        hospedagemDao.remover(hospedagem.getId());
        return hospedagem;
     }

     public Hospedagem recuperar(int id) {
         return hospedagemDao.recuperar(id).orElseThrow(() -> new EntidadeNaoEncontradaException("Hospedagem com id = " + id + " não encontrada."));
     }

     public List<Hospedagem> recuperarTodasAsHospedagensDeUmCliente(int id) {
         Cliente cliente = clienteService.recuperar(id);
         return cliente.getHospedagens();
     }

    public List<Acomodacao> recuperarAcomodacoesDisponiveis(LocalDateTime dataHoraEntradaPrevista, LocalDateTime dataHoraSaidaPrevista) {
        validarPeriodo(dataHoraEntradaPrevista, dataHoraSaidaPrevista);
        return acomodacaoService.recuperarTodos()
            .stream()
            .filter(acomodacao -> acomodacao.getHospedagens()
                .stream()
                .noneMatch(umaHospedagem -> periodosSeSobrepoem(umaHospedagem, dataHoraEntradaPrevista, dataHoraSaidaPrevista)))
            .toList();
    }

    public boolean vazio() {
        return hospedagemDao.vazio();
    }

    public List<Hospedagem> recuperarTodos() {
        return hospedagemDao.recuperarTodos();
    }

    private void validarPeriodo(LocalDateTime dataHoraEntradaPrevista, LocalDateTime dataHoraSaidaPrevista) {
        if (!dataHoraSaidaPrevista.isAfter(dataHoraEntradaPrevista)) {
            throw new PeriodoDeHospedagemInvalidoException(
                "A data e hora de saída prevista deve ser posterior a " +
                    Util.formatarDataHora(dataHoraEntradaPrevista));
        }
    }

    private boolean periodosSeSobrepoem(Hospedagem umaHospedagem, LocalDateTime dataHoraEntradaPrevista, LocalDateTime dataHoraSaidaPrevista) {
        return dataHoraEntradaPrevista.isBefore(recuperarDataHoraSaida(umaHospedagem)) &&
            dataHoraSaidaPrevista.isAfter(umaHospedagem.getDataHoraEntradaPrevista());
    }

    private LocalDateTime recuperarDataHoraSaida(Hospedagem umaHospedagem) {
        if (umaHospedagem.getDataHoraSaidaReal() != null) {
            return umaHospedagem.getDataHoraSaidaReal();
        }
        return umaHospedagem.getDataHoraSaidaPrevista();
    }

}
