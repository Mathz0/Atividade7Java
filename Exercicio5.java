import java.math.BigDecimal;
import java.util.regex.Pattern;

// Exceção personalizada
class PagamentoInvalidoException extends RuntimeException {
    public PagamentoInvalidoException(String msg) {
        super(msg);
    }
}

// Classe abstrata
abstract class FormaPagamento {
    public abstract void validarPagamento();
    public abstract void processarPagamento(BigDecimal valor);
}

// Subclasse Cartão de Crédito
class CartaoCredito extends FormaPagamento {
    private String numeroCartao;

    public CartaoCredito(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    @Override
    public void validarPagamento() {
        if (numeroCartao == null || !numeroCartao.matches("\\d{16}")) {
            throw new PagamentoInvalidoException("Número de cartão inválido! Deve conter 16 dígitos.");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        validarPagamento();
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoInvalidoException("Valor do pagamento deve ser positivo!");
        }
        System.out.println("Pagamento de R$" + valor + " processado no cartão " + numeroCartao);
    }
}

// Subclasse Boleto
class Boleto extends FormaPagamento {
    private String codigoBarras;

    public Boleto(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    @Override
    public void validarPagamento() {
        if (codigoBarras == null || !codigoBarras.matches("\\d{47}")) {
            throw new PagamentoInvalidoException("Código de barras inválido! Deve conter 47 dígitos.");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        validarPagamento();
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoInvalidoException("Valor do pagamento deve ser positivo!");
        }
        System.out.println("Pagamento de R$" + valor + " processado via boleto: " + codigoBarras);
    }
}

// Subclasse Pix
class Pix extends FormaPagamento {
    private String chavePix;

    public Pix(String chavePix) {
        this.chavePix = chavePix;
    }

    @Override
    public void validarPagamento() {
        if (chavePix == null || chavePix.isEmpty()) {
            throw new PagamentoInvalidoException("Chave PIX não pode ser nula ou vazia!");
        }

        // Aceita formatos: e-mail, CPF numérico ou celular
        boolean formatoValido =
                Pattern.matches("\\d{11}", chavePix) ||                  // CPF
                Pattern.matches("\\d{9,13}", chavePix) ||                // Celular
                Pattern.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", chavePix); // Email

        if (!formatoValido) {
            throw new PagamentoInvalidoException("Chave PIX inválida!");
        }
    }

    @Override
    public void processarPagamento(BigDecimal valor) {
        validarPagamento();
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoInvalidoException("Valor do pagamento deve ser positivo!");
        }
        System.out.println("Pagamento de R$" + valor + " processado via PIX para chave: " + chavePix);
    }
}

// Programa principal
public class Main {
    public static void main(String[] args) {
        FormaPagamento[] pagamentos = {
            new CartaoCredito("1234567890123456"),
            new Boleto("2379338128600000001234567890123456789012345"),
            new Pix("teste@pix.com"),
            new Pix("12345678901") // CPF válido
        };

        for (FormaPagamento pagamento : pagamentos) {
            try {
                pagamento.processarPagamento(new BigDecimal("250.00"));
            } catch (PagamentoInvalidoException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }

        // Exemplo com erro
        try {
            FormaPagamento invalido = new CartaoCredito("1234");
            invalido.processarPagamento(new BigDecimal("100.00"));
        } catch (PagamentoInvalidoException e) {
            System.out.println("Erro (teste inválido): " + e.getMessage());
        }
    }
}
