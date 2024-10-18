package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Biblioteca {

    private String nome;
    private List<Livro> livros = new ArrayList<>();

    public Biblioteca(String nome,Livro livros) {
        this.nome = nome;
    }
    public Biblioteca(String nome ) {

    }

    public void adicionarLivro(Livro livro){
        if (livro == null || livro.getAutor() == null ||
                livro.getAutor().trim().isEmpty() || livro.getTitulo() == null||
        livro.getTitulo().trim().isEmpty() || livro.getDataPublicacao() == null ){
            throw new ArgumentoInvalidoException("Valor invalido");
        }

        this.livros.add(livro);
    }

    public void removerLivroPorTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new ArgumentoInvalidoException("Título inválido");
        }

        boolean removido = livros.removeIf(livro -> livro.getTitulo().equalsIgnoreCase(titulo));

        if (!removido) {
            throw new LivroNaoEncontradoException("Livro não foi encontrado");
        }
    }

    public Livro buscarLivroPorTitulo(String titulo){
        if (titulo ==null || titulo.trim().isEmpty()){
            throw  new ArgumentoInvalidoException("Livro não foi encontrado");
        }

        return livros.stream().filter(livro -> livro.getTitulo()
                .equalsIgnoreCase(titulo))
                .findFirst()
                .orElseThrow(() -> new LivroNaoEncontradoException("Livro não foi encotrado"));



    }

    public Integer contarLivros(){
        return (int) livros.stream().count();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        if (ano == null) {
            throw new IllegalArgumentException("Ano não pode ser nulo");
        }

        LocalDate dataReferencia = LocalDate.of(ano, 12, 31);

        return this.livros.stream()
                .filter(livro -> livro.getDataPublicacao().isBefore(dataReferencia) ||
                        livro.getDataPublicacao().isEqual(dataReferencia))
                .collect(Collectors.toList());
    }

    public List<Livro> retornarTopCincoLivros(){
        return this.livros.stream().sorted(Comparator.comparingDouble(Livro::calcularMediaAvaliacoes)
                .reversed())
                .limit(5)
                .collect(Collectors.toList());
    }











    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
