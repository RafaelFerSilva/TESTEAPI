**Cenários de teste**

Observação para o endpoint relacionado a operação GET:
O endipoint enviado está errado: 

``localhost:8080/{ddd}/{numero}``

Está faltando o recurso /pessoas:

``localhost:8080/pessoas/{ddd}/{numero}``


---


**Cenário: Deve ser possível salvar uma pessoa com todos os dados corretos**

Dado que todos os dados da pessoa estejam preenchidos corretos (cpf e telefônes únicos)

Quando a requisição for submetida

Então os dados da pessoa serão salvos no sistema

---

**Cenário: Não deve ser possível salvar uma pessoal com todos os dados em branco**

Dado que todos os dados da pessoa estejam em branco

Quando a requisição for submetida

Então deve ser retornado um erro

BUG -> Podemos cadastrar usuários com todos os dados em branco

---


**Cenário Não deve ser possível salvar uma pessoal com o telefone em branco**

Dado que o telefone da pessoa esteja em branco (ddd e numero)

Quando a requisição for submetida

Então deve ser retornado um erro

BUG -> Podemos cadastrar pessoas com telefone em branco

---


**Cenário: Não deve ser possível cadastrar uma pessoa com o ddd ou numero do telefone em branco**

Dado que o ddd ou o numero do telefone esteja em branco

Quando a requisição for submetida

Então deve ser retornado um erro

BUG -> Podemos cadastrar pessoas com ddd ou numero de telefone em branco

---

**Cenário: Não deve ser possível salvar uma pessoa com o telefone inválido**

Dado que telefone da pessoa seja inválido (ex: 00000000000)

Quando a requisição for submetida

Então deve ser retornado um erro

BUG -> Podemos cadastrar pessoas com telefone inválido

---


**Cenário: Não deve ser possível salvar uma pessoa com o telefone sendo letras**

Dado que foi adicionado letras no lugar do telefone da pessoa

Quando a requisição for submetida

Então deve ser retornado um erro

BUG -> Podemos cadastrar pessoas com telefone sendo letras

---

**Cenário: Não deve ser possível salvar duas pessoas com o mesmo telefone**

Dado que seja adicionado um telefone já cadastrada na base de dados para outra pessoa

Quando a requisição for submetida

Então deve ser retornado um erro informando que já existe uma pessoa cadastrada com o telefone

---

**Cenário: Não deve ser possível salvar duas pessoas com o mesmo cpf**

Dado que seja adicionado um cpf já cadastrada na base de dados para outra pessoa

Quando a requisição for submetida

Então deve ser retornado um erro informando que já existe uma pessoa cadastrada com o cpf

---

**Cenário: Não deve ser possivel salva uma pessoa com cpf em branco ou null**

Dado que seja adicionado um cpf em branco ou null para uma pessoa

Quando a requisição for submetida

Então deve ser retornado um erro

---

**Cenário: Não deve ser possível salvar uma pessoa com um cpf inválido (Ex: 99999999999)**

Dado que seja adicionado um cpf inválido para uma pessoa

Quando a requisição for submetida

Então deve ser retornado um erro

BUG -> Podemos cadastrar pessoas com cpf inválido

---

**Cenário: Não deve ser possível salvar uma pessoa com um cpf de letras (EX: ASDCXSDERFG)**

Dado que seja adicionado um cpf somente com letras para a pessoa

Quando a requisição for submetida

Então deve ser retornado um erro

------------------------------------------------------------------------------------------------------------------


**Cenário: Deve retornar uma mensagem para busca de telefone inexistente**

Dado que seja feita uma busca por um telefone inexistente na base de dados

Quando a requisição for submetida

Então deve ser retornada uma mensagem que nçai este pessoa com o telefone enviado

---



**Cenário: Deve ser possível buscar uma pessoa pelo seu telefone**

Dado que seja feita uma busca por um telefone salvo na base de dados

Quando a requisição for submetida

Então deve ser retornado os dados do usuário referente aquele telefone

