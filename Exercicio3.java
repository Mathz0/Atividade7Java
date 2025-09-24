import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

// Classe base
abstract class Funcionario {
    protected String nome;
    protected BigDecimal salario;

    public Funcionario(String nome, BigDecimal salario) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio!");
        }
        if (salario == null || salario.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Salário deve ser positivo!");
        }
        this.nome = nome;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    // Método abstrato para ser sobrescrito
    public abstract BigDecimal calcularBonus();
}

// Subclasse Gerente
class Gerente extends Funcionario {
    public Gerente(String nome, BigDecimal salario) {
        super(nome, salario);
    }

    @Override
    public BigDecimal calcularBonus() {
        return salario.multiply(BigDecimal.valueOf(0.20));
    }
}

// Subclasse Desenvolvedor
class Desenvolvedor extends Funcionario {
    public Desenvolvedor(String nome, BigDecimal salario) {
        super(nome, salario);
    }

    @Override
    public BigDecimal calcularBonus() {
        return salario.multiply(BigDecimal.valueOf(0.10));
    }
}

// Programa de teste
public class MainExercicio3 {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Gerente("Alice", new BigDecimal("10000")));
        funcionarios.add(new Desenvolvedor("Bob", new BigDecimal("5000")));
        funcionarios.add(new Desenvolvedor("Carol", new BigDecimal("7000")));
        funcionarios.add(new Gerente("David", new BigDecimal("12000")));

        for (Funcionario f : funcionarios) {
            System.out.println(f.getNome() +
                    " | Salário: R$" + f.getSalario() +
                    " | Bônus: R$" + f.calcularBonus());
        }
    }
}
