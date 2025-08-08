<!-- - Visualizar todas as pessoas do DEI numa tabela de consulta rápida, contendo pelo menos nome, IST ID, email e tipo; -->
<!-- - Adicionar novas pessoas ao sistema; -->
<!-- - Atualizar e remover pessoas existentes; -->
<!-- - Atribuir papéis (roles) no frontend: staff, estudante, professor, coordenador, SC; -->
- Visualizar todos os alunos numa tabela de consulta rápida, contendo pelo menos nome, IST ID, email e estado do workflow de tese/defesa;
- Filtrar alunos por estado do workflow de tese/defesa ou outro campo relevante;
- Visualizar a página individual de cada aluno, incluindo:
  - Secção de informações pessoais;
  - Workflow de tese;
  - Workflow de defesa;
- Atualizar estados dos workflows conforme as etapas descritas;
- Reverter estados dos workflows conforme as etapas descritas;

  - Página de estatísticas dos dados do sistema, como por exemplo o número de alunos por estado do workflow de tese/defesa, notas médias, etc.;
  - Logs detalhados de ações feitas no sistema, como submissões, aprovações e atualizações nos workflows;
  - Autenticação de alunos no backend via Spring Security;
  - Uma homepage com os dados de cada utilizador, reais para o role de student (pois este é o único role que exige criação de conta e autenticação) e genéricos nos restantes roles;
  - Populate com um dump da base de dados;
  - Sistema de envio de [emails](https://github.com/tweedegolf/mailcrab).

