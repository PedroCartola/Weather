package myproject.weatherapi.validator;

import java.util.ArrayList;
import java.util.List;
import static myproject.weatherapi.util.DialogUtil.showError;

public class UsuarioValidator {
    // Método principal de validação que combina todas as regras

    public boolean validarUsuario(String email, String senha) {
        // Lista de validadores que serão aplicados sequencialmente
        List<Validador<String>> validadores = new ArrayList<>();

        // Adicionando os validadores de campos obrigatórios
        validadores.add(new CampoObrigatorioValidador("E-mail", email));
        validadores.add(new CampoObrigatorioValidador("Senha", senha));
        // Adicionando o validador específico de formato de e-mail (aplicado ao campo email)
        validadores.add(new EmailValidador(email));

        // Itera sobre a lista de validadores
        for (Validador<String> validador : validadores) {
            // Cada validador testa seu valor específico
            if (!validador.validar(validador.getValor())) {
                // O validador agora "conhece" o valor que vai validar
                showError(validador.getMensagemErro()); // você usaria DialogUtil.showWarning(mensagem);
                return false; // Retorna falso na primeira falha de validação
            }
        }

        return true; // Todos os validadores passaram
    }

}
