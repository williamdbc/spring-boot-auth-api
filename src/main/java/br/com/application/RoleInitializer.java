package br.com.application;

import br.com.application.controller.RoleController;
import br.com.application.entity.Role;
import br.com.application.enums.UserRoleEnum;
import br.com.application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class RoleInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        createRoles();
    }

    private void createRoles(){
        saveRole(UserRoleEnum.ADMIN,
                "Responsável por configurar o sistema, gerenciar usuários e definir permissões. Tem controle total sobre todos os aspectos do sistema e pode realizar qualquer operação, garantindo a integridade e segurança do ambiente.");

        saveRole(UserRoleEnum.PROJECT_MANAGER,
                "Planeja e coordena projetos, gerencia equipes de trabalho e recursos, define prazos e monitora o progresso do projeto. Atua como o principal ponto de contato entre as partes interessadas e a equipe de desenvolvimento.");

        saveRole(UserRoleEnum.DEVELOPER,
                "Trabalha na implementação e manutenção do código do sistema. Envolvido na criação de novas funcionalidades, correção de erros e melhorias de desempenho, colaborando com a equipe para garantir que os requisitos do projeto sejam atendidos.");

        saveRole(UserRoleEnum.TESTER,
                "Realiza testes para garantir que o sistema esteja funcionando corretamente e que atenda aos requisitos especificados. Identifica e reporta bugs e inconsistências, garantindo a qualidade e a usabilidade do software antes da liberação final.");

        saveRole(UserRoleEnum.ANALYST,
                "Avalia e documenta os requisitos dos projetos, realiza a análise de processos e propõe soluções para melhorias. Trabalha com os stakeholders para entender as necessidades e traduzir essas necessidades em especificações técnicas para a equipe de desenvolvimento.");

        saveRole(UserRoleEnum.USER,
                "Utiliza o sistema para executar tarefas diárias e acessar informações conforme as permissões atribuídas. Tem acesso às funcionalidades básicas e pode interagir com o sistema para realizar atividades operacionais sem direitos administrativos.");
    }

    private void saveRole(UserRoleEnum roleEnum, String description) {
        if(roleRepository.findByName(roleEnum) == null){
            Role role = new Role();
            role.setName(roleEnum);
            role.setDescription(description);
            roleRepository.save(role);
        }
    }

}
