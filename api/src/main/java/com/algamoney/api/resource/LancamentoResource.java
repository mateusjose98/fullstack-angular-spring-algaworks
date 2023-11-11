package com.algamoney.api.resource;

import com.algamoney.api.event.RecursoCriadoEvent;
import com.algamoney.api.exceptionhandler.AlgaMoneyExceptionHandler;
import com.algamoney.api.model.Lancamento;
import com.algamoney.api.model.Pessoa;
import com.algamoney.api.repository.LancamentoRepository;
import com.algamoney.api.repository.filter.LancamentoFilter;
import com.algamoney.api.repository.projection.ResumoLancamento;
import com.algamoney.api.service.LancamentoService;
import com.algamoney.api.service.exception.PessoaInexistenteOuInativaException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("lancamentos")
@RequiredArgsConstructor
public class LancamentoResource {

    final LancamentoRepository lancamentoRepository;
    final ApplicationEventPublisher publisher;
    final LancamentoService lancamentoService;
    final MessageSource messageSource;

    @GetMapping("{id}")
    public ResponseEntity<Lancamento> porId(@PathVariable Long id) {
        var l = lancamentoRepository.findById(id);
        return l.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }
    @GetMapping
    public ResponseEntity<Page<Lancamento>> pesquisar(LancamentoFilter filter, Pageable pageable) {
        var l = lancamentoRepository.filtrar(filter, pageable);
        return ResponseEntity.ok(l);

    }

    @GetMapping("resumo")
    public ResponseEntity<Page<ResumoLancamento>> pesquisarResumo(LancamentoFilter filter,
                                                                  Pageable pageable) {
        var l = lancamentoRepository.resumir(filter, pageable);
        return ResponseEntity.ok(l);

    }

    @PostMapping
    public ResponseEntity<Lancamento> criar(@Valid @RequestBody
                                                Lancamento lancamento, HttpServletResponse response) {
        Lancamento saved = lancamentoService.salvar(lancamento);
        publisher.publishEvent(new RecursoCriadoEvent(this, response, saved.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @ExceptionHandler(PessoaInexistenteOuInativaException.class)
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        var messageUser = messageSource
                .getMessage("pessoa.inexistente-inativa",
                        null, LocaleContextHolder.getLocale());
        var messageDev = ex.toString();
        AlgaMoneyExceptionHandler.Erro erro = new AlgaMoneyExceptionHandler.Erro();
        erro.setMsgDev(messageDev);
        erro.setMsgUsuario(messageUser);
        return ResponseEntity.badRequest().body(erro);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long id) {
        lancamentoRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lancamento> atualizar(@PathVariable Long id, @Valid @RequestBody Lancamento lancamento) {
        try {
            Lancamento lancamentoSalvo = lancamentoService.atualizar(id, lancamento);
            return ResponseEntity.ok(lancamentoSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
