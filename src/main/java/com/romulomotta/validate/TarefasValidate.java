package com.romulomotta.validate;

import com.jayway.jsonpath.internal.Utils;
import com.romulomotta.enums.StatusTarefa;
import com.romulomotta.model.Tarefa;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

public class TarefasValidate {

    protected static final String MENSAGEM_NOT_FOUND_CONSULTA =
            "Nenhum document encontrado para a consulta solicitada";
    protected static final String MENSAGEM_NOT_FOUND_ALTERACAO =
            "Nenhum document encontrado para a alteração solicitada";
    protected static final String MENSAGEM_NOT_FOUND_EXCLUSAO =
            "Nenhum document encontrado para a exclusão solicitada";
    protected static final String MENSAGEM_BAD_REQUEST_ALTERACAO_TAREFA =
            "Dados inválidos para efetuar a alteração da tarefa informada";

    protected void trataListaTarefasNotFound(List<Tarefa> tarefas) {
        if(tarefas == null || tarefas.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, MENSAGEM_NOT_FOUND_CONSULTA);
        }
    }

    protected void trataTarefaNotFound(Tarefa tarefa, String mensagem) {
        if (tarefa == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, mensagem);
        }
    }

    protected Tarefa trataCamposAlteracao(Tarefa tarefa, Tarefa tarefaToUpdate) {
        if (tarefa == null || todosCamposVazios(tarefa)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, MENSAGEM_BAD_REQUEST_ALTERACAO_TAREFA);
        }

        if (!ObjectUtils.isEmpty(tarefa.getStatusTarefa())) {
            tarefaToUpdate.setStatusTarefa(tarefa.getStatusTarefa());
            if (StatusTarefa.CONCLUIDA.equals(tarefa.getStatusTarefa())) {
                tarefaToUpdate.setDataConclusao(LocalDate.now());
            }
        }
        return tarefaToUpdate;
    }


    protected boolean todosCamposVazios(Tarefa tarefa) {
        return ObjectUtils.isEmpty(tarefa.getDescricao()) && ObjectUtils.isEmpty(tarefa.getStatusTarefa());
    }
}
