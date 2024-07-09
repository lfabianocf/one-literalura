package com.LiterAlura.service;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> tclasse);

}
