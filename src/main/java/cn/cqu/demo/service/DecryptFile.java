package cn.cqu.demo.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

public interface DecryptFile {
    void Decrypter(InputStream InputStream) throws IOException;
}