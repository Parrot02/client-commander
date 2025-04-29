package com.fatinfo.clientecomandante;
import java.io.File; 
import java.io.FileWriter; 
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class logGerenciador {
    public void gerarLog(String log) throws IOException {

        FileWriter logFile = new FileWriter("log.txt", true);
        logFile.write(log + "\n");
        logFile.close();
    }
}
