<h1 align="center">Microserviços de contas dos clientes - Empresa de Software</h1>
<p align="center"><i>Repositório responsável por armazenar o microserviço que cria as contas dos clientes.</i></p>

##  Sobre esse projeto
Este é um projeto que deve ser rodado após o Eureka Server estar rodando, para que se registre no discovery server.


## Indíce
<!--ts-->
   * [Como usar?](#como-usar)
   * [Endpoints](#endpoints)
   * [Testes](#testes)
   * [Creditos](#creditos)
<!--te-->
  
<h1>Como usar?</h1>
<h2>Prerequisites</h2>
<p>O Eureka Server deve estar rodando, acesse-o <a href="https://github.com/ValterGabriell/bank-system-eureka-server">aqui</a>.</br>
<p>Clone ou baixe o repositório e start ele através de sua IDE de preferência rodando o método main da classe principal na pasta raíz da aplicação, feito isso, basta começar a usar :). O ideal é startar todos os outros microserviços antes de testar a aplicação.</p>

1 -> <a href="https://github.com/ValterGabriell/bank-system-eureka-server">Eureka Server</a></br>
2 -> <a href="https://github.com/ValterGabriell/bank-system-mscards">Microserviço responsável por criar cartões para os usuários</a></br>
3 -> <a href="https://github.com/ValterGabriell/bank-system-mscreditappraiser">Microserviço responsável verificar o crédito que o usuário terá e solicitar a emissão de cartão</a></br>
4 -> <a href="https://github.com/ValterGabriell/software-company-mslead">Microserviço responsável pela criação dos líderes das squads</a></br>
5 -> <a href="https://github.com/ValterGabriell/software-company-mscolaborators">Microserviço responsável pela criação dos colaboradores das squads</a></br>
6 -> <a href="https://github.com/ValterGabriell/bank-system-gateway">Gateway para fazer o loadbalancer dos microserviços</a></br>


  
<h1>Endpoints</h1>

Uma conta é criada sempre que um líder ou colaborador for criado!

<h3>BASE URL</h3>

```bash
http://localhost:8080/account
``` 
<h1>GET</h1></br>


<h2>Recuperar informações do cliente</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/</td>
    <td>recupera informações do cliente</td>
    <td>id</td>
  </tr>
</table>



<h3>Resposta esperada</h3></br>

```bash
{
	"cpf": "22324672912",
	"birthDate": "2005-06-25",
	"accountDate": "2023-04-10",
	"clientName": "nome teste",
	"clientPhoneNumber": "5589541222659",
	"clientEmail": "email@gmail.com",
	"gender": "FEMALE",
	"password": "12345",
	"income": 6500.00
}
```


<h1>DELETE</h1></br>


<h2>Deletar conta</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
    <th>Query</th>
  </tr>
  <tr>
    <td>/</td>
    <td>delete informações do cliente</td>
    <td>id</td>
  </tr>
</table>

<h1>Testes</h1>

```
 @DisplayName(value = "Verify if id has 11 digits ")
    @ParameterizedTest
    @ValueSource(strings = {"12345678945"})
    void itShouldReturnTrueWhenIdHasElevenDigitsAndFalseWhenIsNot(String id){
        int length = id.length();
        assertEquals(11, length);
    }

    @DisplayName(value = "Verify if id has 14 ")
    @ParameterizedTest
    @ValueSource(strings = {"12345678912345"})
    void itShouldReturnTrueWhenIdHas14DigitsAndFalseWhenIsNot(String id){
        int length = id.length();
        assertEquals(14, length);
    }

    @DisplayName(value = "Verify if id is bigger than 11 and return true if it is 14")
    @ParameterizedTest
    @ValueSource(strings = {"12345678945236"})
    void itShouldReturnTrueWhenIdisBiggerThan11Andis14(String id){
        int length = id.length();
        if (length > 11){
            assertEquals(14, length);
        }
    }


    @DisplayName(value = "Verify if id has just one type of digit")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void itShouldReturnTrueIfCpfHasJustOneTypeOfNumber(String value){
        String newString = "1".repeat(11);
        assertEquals(value, newString);
    }

    @DisplayName(value = "Verify if phone number has 13 digits")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945","1234567894562"})
    void itShouldReturnTrueWhenPhoneNumberHasFourteenDigitsAndFalseWhenIsNot(String phone){
        assertEquals(13, phone.length());
    }

    @DisplayName(value = "Verify if phone starts with 55")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945","12345678945612", "553256"})
    void itShouldReturnTrueWhenPhoneNumberStartsWithFive(String phone){
        assertEquals("55", phone.substring(0, 2));
    }

    @DisplayName("Verify if is email valid")
    @ParameterizedTest
    @ValueSource(strings = {"vgabrielbri@hotmail.com", "用户名@领域.电脑"})
    void itShouldReturnTrueIfEmailIsValid(String email){
        String regexPattern = EMAIL_PATTERN;
        assertTrue(Pattern.compile(regexPattern).matcher(email).matches());
    }
    
```

<h1>Creditos</h1>



<a href="https://www.linkedin.com/in/valter-gabriel">
  <img style="border-radius: 50%;" src="https://user-images.githubusercontent.com/63808405/171045850-84caf881-ee10-4782-9016-ea1682c4731d.jpeg" width="100px;" alt=""/>
  <br />
  <sub><b>Valter Gabriel</b></sub></a> <a href="https://www.linkedin.com/in/valter-gabriel" title="Linkedin">🚀</ a>
 
Made by Valter Gabriel 👋🏽 Get in touch!

[![Linkedin Badge](https://img.shields.io/badge/-Gabriel-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/valter-gabriel/ )](https://www.linkedin.com/in/valter-gabriel/)

