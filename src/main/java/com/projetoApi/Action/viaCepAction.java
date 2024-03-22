package com.projetoApi.Action;

import com.projetoApi.Model.viaCep;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.swing.JOptionPane;
import java.io.IOException;
import java.net.URL;

public class viaCepAction {
    public static void run() {
        String cep = JOptionPane.showInputDialog("Digite o CEP:");
        if (cep != null && !cep.isEmpty()) {
            try {
                URL url = new URL("https://viacep.com.br/ws/" + cep + "/xml/");
                XmlMapper xmlMapper = new XmlMapper();
                xmlMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
                viaCep address = xmlMapper.readValue(url, viaCep.class);

                String message = "Cidade: " + address.getLocalidade() + "\n"
                        + "UF: " + address.getUf();

                JOptionPane.showMessageDialog(null, message);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar o endereço: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "CEP não fornecido.");
        }
    }
}
