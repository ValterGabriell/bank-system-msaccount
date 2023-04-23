package io.github.valtergabriell.msaccount.application.exception;

public class ExceptionsValues {
    public static final String CPF_INVALID = "CPF inválido!";
    public static final String DIGITS_REPEATLY = "Valores inválidos, numeros repetidos!";
    public static final String ALREADY_ON_DATABASE = "CPF já cadastrado no nosso banco de dados!!!";
    public static final String ACCOUNT_CREATED = "Conta criada com sucesso!";
    public static final String AGE_INVALID = "Você precisa ter mais de 17 anos para abrir uma conta!";
    public static final String USER_NOT_FOUND = "Usuário não encontrado";
    public static int CPF_COUNT = 11;
    public static int PHONE_NUMBER_COUNT = 13;
    public static String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
            + "[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
}
