public class Produto {
    // Atributos privados
    private String nome;
    private double preco;
    private int quantidadeEmEstoque;

    // Construtor
    public Produto(String nome, double preco, int quantidadeEmEstoque) {
        setNome(nome); // usa setters para validar
        setPreco(preco);
        setQuantidadeEmEstoque(quantidadeEmEstoque);
    }

    // Getter e Setter de nome
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio!");
        }
        this.nome = nome;
    }

    // Getter e Setter de preco
    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo!");
        }
        this.preco = preco;
    }

    // Getter e Setter de quantidadeEmEstoque
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        if (quantidadeEmEstoque < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa!");
        }
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    // Método para exibir informações
    @Override
    public String toString() {
        return "Produto: " + nome +
               ", Preço: R$" + preco +
               ", Quantidade em estoque: " + quantidadeEmEstoque;
    }

    // Método main para demonstração
    public static void main(String[] args) {
        try {
            // Criando produto válido
            Produto p1 = new Produto("Notebook", 3500.00, 10);
            System.out.println(p1);

            // Alterando para valores válidos
            p1.setPreco(3200.00);
            p1.setQuantidadeEmEstoque(8);
            System.out.println("Após atualização: " + p1);

            // Tentando atribuir nome inválido
            p1.setNome(""); 
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            // Tentando criar produto com preço negativo
            Produto p2 = new Produto("Celular", -1500.00, 5);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        try {
            // Tentando alterar estoque para valor inválido
            Produto p3 = new Produto("Mouse", 80.00, 15);
            p3.setQuantidadeEmEstoque(-3);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
