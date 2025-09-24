// Classe de exceção personalizada (opcional)
class DescontoInvalidoException extends RuntimeException {
    public DescontoInvalidoException(String mensagem) {
        super(mensagem);
    }
}

public class Produto {
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    // Construtor
    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        setNome(nome);
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    // Getters e Setters com validações
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio!");
        }
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo!");
        }
        this.preco = preco;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa!");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    // Método de desconto com validação
    public void aplicarDesconto(double porcentagem) {
        if (porcentagem < 0 || porcentagem > 50) {
            throw new DescontoInvalidoException(
                "Desconto deve estar entre 0% e 50%! Valor informado: " + porcentagem + "%"
            );
        }
        double valorDesconto = preco * (porcentagem / 100);
        preco -= valorDesconto;
    }

    @Override
    public String toString() {
        return "Produto: " + nome +
               ", Preço: R$" + String.format("%.2f", preco) +
               ", Quantidade em estoque: " + quantidadeEmEstoque;
    }

    // Demonstração
    public static void main(String[] args) {
        Produto p1 = new Produto("Notebook", 3000.00, 5);

        System.out.println("Preço original: R$" + p1.getPreco());

        // Aplicando desconto válido
        p1.aplicarDesconto(10);
        System.out.println("Após 10% de desconto: R$" + p1.getPreco());

        // Aplicando outro desconto válido
        p1.aplicarDesconto(50);
        System.out.println("Após 50% de desconto: R$" + p1.getPreco());

        // Tentando desconto inválido (negativo)
        try {
            p1.aplicarDesconto(-5);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Tentando desconto inválido (acima de 50)
        try {
            p1.aplicarDesconto(60);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
