package com.algamoney.api.event;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class RecursoCriadoEvent extends ApplicationEvent {

    private HttpServletResponse response;
    private Long id;

    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long id) {
        super(source);
        this.response = response;
        this.id = id;
    }
}
