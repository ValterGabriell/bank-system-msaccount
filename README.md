<h1 align="center">Microserviços de contas dos clientes - Sistema Bancário</h1>
<p align="center"><i>Repositório responsável por armazenar o microserviço que cria as contas dos clientes.</i></p>

##  Sobre esse projeto
Este é um projeto que deve ser rodado após o Eureka Server estar rodando, para que se registre no discovery server.


## Indíce
<!--ts-->
   * [Como usar?](#como-usar)
   * [Endpoints](#endpoints)
   * [Testes](#testes)
   * [Credits](#credits)
<!--te-->
  
<h1>Como usar?</h1>
<h2>Prerequisites</h2>
<p>O Eureka Server deve estar rodando, acesse-o <a href="https://github.com/ValterGabriell/bank-system-eureka-server">aqui</a>.</br>
<p>Clone ou baixe o repositório e start ele através de sua IDE de preferência rodando o método main da classe principal na pasta raíz da aplicação, feito isso, basta começar a usar :). O ideal é startar todos os outros microserviços antes de testar a aplicação.</p>

1 -> <a href="https://github.com/ValterGabriell/bank-system-eureka-server">Eureka Server</a></br>
2 -> <a href="https://github.com/ValterGabriell/bank-system-mscards">Microserviço responsável por criar cartões para os usuários</a></br>
3 -> <a href="https://github.com/ValterGabriell/bank-system-mscreditappraiser">Microserviço responsável verificar o crédito que o usuário terá e solicitar a emissão de cartão</a></br>
4 -> <a href="https://github.com/ValterGabriell/bank-system-gateway">Gateway para fazer o loadbalancer dos microserviços</a></br>


  
<h1>Endpoints</h1>
<h3>BASE URL</h3>

```bash
http://localhost:8080/account
``` 
<h1>POST</h1></br>

<h2>Criar conta</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/</td>
    <td>criar conta</td>
  </tr>
</table>

<h3>Request esperada</h3></br>

```bash
{
	"cpf":"22324672912",
	"birthDate":"2005-06-25",
	"clientName":"nome teste",
	"clientPhoneNumber":"5589541222659",
	"clientEmail":"email@gmail.com",
	"gender":"FEMALE",
	"password":"12345",
	"income":6500
}
```



<h3>Resposta esperada</h3></br>

```bash
{
	"data": {
		"cpf": "22324672912",
		"birthDate": "2005-06-25",
		"accountDate": "2023-04-12",
		"clientName": "nome teste",
		"clientPhoneNumber": "5589541222659",
		"clientEmail": "email@gmail.com",
		"gender": "FEMALE",
		"income": 6500
	},
	"message": "Conta criada com sucesso!",
	"headerLocation": "http://localhost:9090?cpf=22324672912"
}
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
    <td>cpf cliente</td>
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

<h1>Testes</h1>

<h3>Verifica se o CPF tem 11 digitos</h3></br>

```
    @DisplayName(value = "Verify if CPF has 11 digits")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945"})
    void itShouldReturnTrueWhenCpfHasElevenDigitsAndFalseWhenIsNot(String cpf){
        assertEquals(11, cpf.length());
    }
    
```


<h3>Verifica se o CPF possui caracteres repetidos em todo o campo</h3></br>

```
    @DisplayName(value = "Verify if CPF has just one type of digit")
    @ParameterizedTest
    @ValueSource(strings = {"1", "2"})
    void itShouldReturnTrueIfCpfHasJustOneTypeOfNumber(String value){
        String newString = "1".repeat(11);
        assertEquals(value, newString);
    }
```



<h3>Verifica se o numero de telefone tem 13 digitos</h3></br>

```
    @DisplayName(value = "Verify if phone number has 13 digits")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945","1234567894562"})
    void itShouldReturnTrueWhenPhoneNumberHasFourteenDigitsAndFalseWhenIsNot(String phone){
        assertEquals(13, phone.length());
    }
```


<h3>Verifica se o numero de telefone inicia com 55</h3></br>

```
    @DisplayName(value = "Verify if phone starts with 55")
    @ParameterizedTest
    @ValueSource(strings = {"", "12345", "65478", "12345678945","12345678945612", "553256"})
    void itShouldReturnTrueWhenPhoneNumberStartsWithFive(String phone){
        assertEquals("55", phone.substring(0, 2));
    }
```


<h3>Verifica se o Email inserido é válido</h3></br>

```
    @DisplayName("Verify if is email valid")
    @ParameterizedTest
    @ValueSource(strings = {"vgabrielbri@hotmail.com", "用户名@领域.电脑"})
    void itShouldReturnTrueIfEmailIsValid(String email){
        String regexPattern = EMAIL_PATTERN;
        assertTrue(Pattern.compile(regexPattern).matcher(email).matches());
    }
```

<h1>Credits</h1>

---

<a href="https://www.linkedin.com/in/valter-gabriel">
  <img style="border-radius: 50%;" src="https://user-images.githubusercontent.com/63808405/171045850-84caf881-ee10-4782-9016-ea1682c4731d.jpeg" width="100px;" alt=""/>
  <br />
  <sub><b>Valter Gabriel</b></sub></a> <a href="https://www.linkedin.com/in/valter-gabriel" title="Linkedin">🚀</ a>
 
Made by Valter Gabriel 👋🏽 Get in touch!

[![Linkedin Badge](https://img.shields.io/badge/-Gabriel-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/valter-gabriel/ )](https://www.linkedin.com/in/valter-gabriel/)

