package br.com.alura.loja.Vo;

import java.time.LocalDate;

/**
 * Created by Lucas Anderson Ladislau Aguiar on 09/09/2022.
 */
public class RelatorioVendasVo {

    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;

    public RelatorioVendasVo(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    @Override
    public String toString() {
        return "RelatorioVendas ==> " +
                "nomeProduto = " + nomeProduto +
                ", quantidade = " + quantidadeVendida +
                ", data ultima venda = " + dataUltimaVenda ;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Long getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public LocalDate getDataUltimaVenda() {
        return dataUltimaVenda;
    }
}
