
<h1>FitLife - Sistema de Gestão de Academia</h1>

FitLife é um sistema de gestão de academia desenvolvido para facilitar o gerenciamento de dados dos alunos, exercícios físicos, fichas de treino, consultas e outros aspectos essenciais para uma academia. Este projeto utiliza tecnologias modernas para oferecer uma plataforma robusta e eficiente.

Funcionalidades Principais
Cadastro de Alunos: Adicionar, remover, atualizar e listar informações dos alunos.
Registro de Exercícios: Gerenciar exercícios físicos realizados pelos alunos.
Consultas: Diversas consultas específicas sobre exercícios, alunos e estatísticas.
Fichas de Treino: Cadastrar, deletar e listar fichas de treino personalizadas.
Administração de Exercícios: Adicionar, remover, atualizar e listar exercícios disponíveis.
Integração com Banco de Dados: Utiliza MongoDB Atlas para armazenamento dos dados.
Desenvolvido com Spring Boot e Java: Plataforma robusta e escalável para desenvolvimento backend.
Tecnologias Utilizadas
Backend: Spring Boot, Java
Banco de Dados: MongoDB Atlas
Outros: RESTful APIs, Maven (gerenciamento de dependências), Git (controle de versão)
Instalação e Uso
Clonar o Repositório:

bash
Copiar código
git clone https://github.com/Pedro-Henrique-Teles/FitLife.git
cd fitlife
Configurar Variáveis de Ambiente:

Configure suas credenciais do MongoDB Atlas no arquivo application.properties.
Executar o Projeto:

Utilize sua IDE favorita para abrir o projeto e execute a classe principal FitLifeApplication.java.
Alternativamente, execute via linha de comando:

mvn spring-boot:run

Acessar a Aplicação:

Após tudo isso, crie uma conta no MongoDB Atlas, crie um cluster e após a database, pegue o link de conexão e cole em application.propeirties

Licença
Este projeto está licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.

