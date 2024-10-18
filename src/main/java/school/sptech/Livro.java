package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Livro {

    private String titulo;
    private String autor;
    private LocalDate dataPublicacao;
    private List<Avaliacao> avaliacoes;

    public Livro(String titulo, String autor, LocalDate dataPublicacao,List<Avaliacao> avaliacoes) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.avaliacoes = new ArrayList<>();

    }



    public void adicionarAvaliacao(String descricao, Double qtdEstrelas){
            if (descricao == null|| descricao.trim().isEmpty()|| qtdEstrelas ==null || qtdEstrelas < 0  || qtdEstrelas > 5 ){
            throw new ArgumentoInvalidoException("Descrição está nula ou quantidade de estrelas está fora do intervalo permitido (0 a 5).");
        }
        this.avaliacoes.add(new Avaliacao(descricao,qtdEstrelas));
    }
    public Double calcularMediaAvaliacoes(){
        if (avaliacoes == null || avaliacoes.isEmpty()){
            return 0.0;
        }
        return avaliacoes.stream().mapToDouble(Avaliacao::getQtdEstrelas).average().getAsDouble();
    }


    @Override public String toString(){
        return "";
    }


    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
