import java.util.ArrayList;
import java.util.List;

// Interface
interface IMeioTransporte {
    void acelerar();
    void frear();
    int getVelocidade();
    String getNome();
}

// Exceção para operações inválidas
class VelocidadeInvalidaException extends RuntimeException {
    public VelocidadeInvalidaException(String msg) {
        super(msg);
    }
}

// Classe Carro
class Carro implements IMeioTransporte {
    private int velocidade = 0;
    private final int VELOCIDADE_MAX = 200;

    @Override
    public void acelerar() {
        if (velocidade + 20 > VELOCIDADE_MAX) {
            throw new VelocidadeInvalidaException("O carro não pode ultrapassar " + VELOCIDADE_MAX + " km/h");
        }
        velocidade += 20;
    }

    @Override
    public void frear() {
        if (velocidade - 10 < 0) {
            throw new VelocidadeInvalidaException("O carro já está parado!");
        }
        velocidade -= 10;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }

    @Override
    public String getNome() {
        return "Carro";
    }
}

// Classe Bicicleta
class Bicicleta implements IMeioTransporte {
    private int velocidade = 0;
    private final int VELOCIDADE_MAX = 40;

    @Override
    public void acelerar() {
        if (velocidade + 5 > VELOCIDADE_MAX) {
            throw new VelocidadeInvalidaException("A bicicleta não pode ultrapassar " + VELOCIDADE_MAX + " km/h");
        }
        velocidade += 5;
    }

    @Override
    public void frear() {
        if (velocidade - 5 < 0) {
            throw new VelocidadeInvalidaException("A bicicleta já está parada!");
        }
        velocidade -= 5;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }

    @Override
    public String getNome() {
        return "Bicicleta";
    }
}

// Classe Trem
class Trem implements IMeioTransporte {
    private int velocidade = 0;
    private final int VELOCIDADE_MAX = 300;

    @Override
    public void acelerar() {
        if (velocidade + 50 > VELOCIDADE_MAX) {
            throw new VelocidadeInvalidaException("O trem não pode ultrapassar " + VELOCIDADE_MAX + " km/h");
        }
        velocidade += 50;
    }

    @Override
    public void frear() {
        if (velocidade - 25 < 0) {
            throw new VelocidadeInvalidaException("O trem já está parado!");
        }
        velocidade -= 25;
    }

    @Override
    public int getVelocidade() {
        return velocidade;
    }

    @Override
    public String getNome() {
        return "Trem";
    }
}

// Programa principal
public class Main {
    public static void main(String[] args) {
        List<IMeioTransporte> transportes = new ArrayList<>();
        transportes.add(new Carro());
        transportes.add(new Bicicleta());
        transportes.add(new Trem());

        for (IMeioTransporte t : transportes) {
            try {
                System.out.println("=== " + t.getNome() + " ===");
                t.acelerar();
                t.acelerar();
                System.out.println("Velocidade após acelerar: " + t.getVelocidade() + " km/h");

                t.frear();
                System.out.println("Velocidade após frear: " + t.getVelocidade() + " km/h");

                // Forçar erro proposital
                while (true) {
                    t.frear();
                }
            } catch (VelocidadeInvalidaException e) {
                System.out.println("Erro: " + e.getMessage());
            }
            System.out.println();
        }
    }
}
