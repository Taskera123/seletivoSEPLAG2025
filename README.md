
🧩 Projeto: Sistema do Processo Seletivo Pleno

Nome: Lucas Henrique Tasca de Araujo
Inscrição: 

✨ Tecnologias Utilizadas

Spring Boot

Java 17

MinIO :latest

PostgreSQL :latest

NGINX :latest

🛠️ Como Executar

Navegue até a pasta sandbox no projeto.

Execute o seguinte comando no terminal dentro dessa pasta:

docker-compose up

Este comando iniciará os seguintes containers:

MinIO

PostgreSQL

NGINX

Aplicação Spring Boot

🧱 Arquitetura da Solução

A aplicação utiliza NGINX como proxy reverso para resolver problemas de geração de links temporários no MinIO.

🧩 Arquitetura do Projeto

O projeto segue os princípios da arquitetura Ports and Adapters (Hexagonal Architecture).

🧲 Como Testar

✅ Acesso à API via Swagger

Acesse a interface Swagger:

http://localhost:8083/apiseletivo/swagger-ui/index.html#/

Autenticação

Execute o serviço POST /auth/login na seção AUTENTICAÇÃO.

Use as credenciais:

Usuário: admin

Senha: password

Copie o accessToken e insira na opção Authorize (canto superior direito).

O token é válido por 5 minutos.

Para renovar o token, utilize o serviço /auth/refresh-token informando o refreshToken e cole o novo token na opção Authorize.

🔄 Funcionalidades (CRUDs)

🏩 CRUD de Cidade (Opcional)

POST /cidade/: Cria uma nova cidade (já existem 5 cidades inseridas no start da aplicação).

GET /cidade/paginado/all: Lista todas as cidades paginadas.

GET /cidade/{cidId}: Busca uma cidade específica.

PUT /cidade/{cidId}: Atualiza uma cidade.

DELETE /cidade/{cidId}: Exclui uma cidade.

🏠 CRUD de Endereço (Opcional)

POST /endereco/: Cria um novo endereço.

Pode criar/editar cidade junto. Se id for passado, cidade é atualizada, caso contrário, uma nova cidade é criada.

GET /endereco/paginado/all: Lista todos os endereços paginados.

GET /endereco/{endId}: Busca um endereço específico.

PUT /endereco/{endId}: Atualiza um endereço.

Também permite criação/edição de cidade como na criação.

DELETE /endereco/{endId}: Exclui um endereço.

🏢 CRUD de Unidade

POST /unidade/: Cria uma nova unidade.

GET /unidade/paginado/all: Lista todas as unidades paginadas.

GET /unidade/{unidId}: Busca uma unidade específica.

PUT /unidade/{unidId}: Atualiza uma unidade.

DELETE /unidade/{unidId}: Exclui uma unidade.

👨‍💼 CRUD de Servidor Efetivo

POST /servidor-efetivo/: Cria um novo servidor efetivo.

Necessário informar a pessoa completa.

Não é necessário informar lista completa de endereços, apenas os IDs.

GET /servidor-efetivo/paginado/all: Lista servidores efetivos paginados.

GET /servidor-efetivo/{pesId}: Busca servidor efetivo por ID.

PUT /servidor-efetivo/{pesId}: Atualiza servidor efetivo.

POST /servidor-efetivo/upload-fotos/{pesId}: Adiciona uma ou mais fotos ao servidor.

DELETE /servidor-efetivo/{pesId}: Exclui o servidor efetivo.

GET /servidor-efetivo/endereco-funcional?nome=...: Busca endereço funcional por parte do nome.

GET /servidor-efetivo/lotados-unidade/{unidId}: Lista servidores efetivos lotados em determinada unidade.

⏳ CRUD de Servidor Temporário

POST /servidor-temporario/: Cria um novo servidor temporário.

Semelhante ao servidor efetivo.

GET /servidor-temporario/paginado/all: Lista servidores temporários paginados.

GET /servidor-temporario/{unidId}: Busca servidor temporário por unidade.

PUT /servidor-temporario/{pesId}: Atualiza servidor temporário.

POST /servidor-efetivo/upload-fotos/{pesId}: (Sim, também funciona para temporários).

DELETE /servidor-temporario/{pesId}: Exclui o servidor temporário.

🧷 CRUD de Lotação

POST /lotacao/: Cria uma nova lotação.

Basta passar os IDs da pessoa e unidade, não os objetos completos.

GET /lotacao/paginado/all: Lista todas as lotações paginadas.

GET /lotacao/{unidId}: Busca uma lotação específica.

PUT /lotacao/{lotId}: Atualiza uma lotação.

DELETE /lotacao/{lotId}: Exclui uma lotação.
