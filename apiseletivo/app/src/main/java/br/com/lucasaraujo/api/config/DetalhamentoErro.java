package br.com.lucasaraujo.api.config;

import org.springframework.http.HttpStatus;

public class DetalhamentoErro {
    private Integer statusCode;

    private String statusMessage;

    private String httpMethod;

    private String msgDesenvolvedor;

    private String msgUsuario;

    private String path;

    public String getPath() {
        return path;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public String getMsgDesenvolvedor() {
        return msgDesenvolvedor;
    }

    public String getMsgUsuario() {
        return msgUsuario;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {

        private DetalhamentoErro erro;

        Builder() {
            this.erro = new DetalhamentoErro();
        }

        public Builder addStatus(HttpStatus status) {
            this.erro.statusCode = status.value();
            this.erro.statusMessage = status.getReasonPhrase();
            return this;
        }

        public Builder addHttpMethod(String method) {
            this.erro.httpMethod = method;
            return this;
        }

        public Builder addMsgDesenvolvedor(String erro) {
            this.erro.msgDesenvolvedor = erro;
            return this;
        }

        public Builder addMsgUsuario(String detalhe) {
            this.erro.msgUsuario = detalhe;
            return this;
        }

        public Builder addPath(String path) {
            this.erro.path = path;
            return this;
        }

        public DetalhamentoErro build() {
            return this.erro;
        }
    }
}
