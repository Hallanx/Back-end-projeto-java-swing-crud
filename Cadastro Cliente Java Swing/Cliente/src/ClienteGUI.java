import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteGUI extends JFrame {
    private ClienteCRUD crud = new ClienteCRUD();
    private JTextField nomeField, cpfField, telField, endField, numField, cidadeField, estadoField;
    private JTextField cpfAlterarField, cpfRemoverField;
    private JTextArea outputArea;

    public ClienteGUI() {
        setTitle("Cadastro de Clientes");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(11, 2));
        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("CPF:"));
        cpfField = new JTextField();
        inputPanel.add(cpfField);

        inputPanel.add(new JLabel("Telefone:"));
        telField = new JTextField();
        inputPanel.add(telField);

        inputPanel.add(new JLabel("Endereço:"));
        endField = new JTextField();
        inputPanel.add(endField);

        inputPanel.add(new JLabel("Número:"));
        numField = new JTextField();
        inputPanel.add(numField);

        inputPanel.add(new JLabel("Cidade:"));
        cidadeField = new JTextField();
        inputPanel.add(cidadeField);

        inputPanel.add(new JLabel("Estado:"));
        estadoField = new JTextField();
        inputPanel.add(estadoField);

        JButton addButton = new JButton("Adicionar Cliente");
        addButton.addActionListener(new AddClienteListener());
        inputPanel.add(addButton);

        JButton listButton = new JButton("Listar Clientes");
        listButton.addActionListener(new ListClienteListener());
        inputPanel.add(listButton);

        inputPanel.add(new JLabel("CPF para Alterar:"));
        cpfAlterarField = new JTextField();
        inputPanel.add(cpfAlterarField);

        JButton updateButton = new JButton("Alterar Cliente");
        updateButton.addActionListener(new UpdateClienteListener());
        inputPanel.add(updateButton);

        inputPanel.add(new JLabel("CPF para Remover:"));
        cpfRemoverField = new JTextField();
        inputPanel.add(cpfRemoverField);

        JButton removeButton = new JButton("Remover Cliente");
        removeButton.addActionListener(new RemoveClienteListener());
        inputPanel.add(removeButton);

        add(inputPanel, BorderLayout.NORTH);

        outputArea = new JTextArea();
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private class AddClienteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String nome = nomeField.getText();
                String cpf = cpfField.getText();
                String tel = telField.getText();
                String end = endField.getText();
                String num = numField.getText();
                String cidade = cidadeField.getText();
                String estado = estadoField.getText();

                if (nome.isEmpty() || cpf.isEmpty() || tel.isEmpty() || end.isEmpty() || num.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos devem ser preenchidos");
                }

                Cliente cliente = new Cliente(nome, cpf, tel, end, num, cidade, estado);
                crud.addCliente(cliente);

                outputArea.setText("Cliente adicionado com sucesso!\n");
                clearFields();
            } catch (NumberFormatException ex) {
                outputArea.setText("Erro: CPF, Telefone e Número devem ser números válidos.\n");
            } catch (IllegalArgumentException ex) {
                outputArea.setText("Erro: " + ex.getMessage() + "\n");
            }
        }

        private void clearFields() {
            nomeField.setText("");
            cpfField.setText("");
            telField.setText("");
            endField.setText("");
            numField.setText("");
            cidadeField.setText("");
            estadoField.setText("");
        }
    }

    private class ListClienteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            outputArea.setText("");
            for (Cliente cliente : crud.listClientes()) {
                outputArea.append(cliente + "\n");
            }
        }
    }

    private class UpdateClienteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long cpf = Long.valueOf(cpfAlterarField.getText().trim());

                String nome = nomeField.getText();
                String tel = telField.getText();
                String end = endField.getText();
                String num = numField.getText();
                String cidade = cidadeField.getText();
                String estado = estadoField.getText();

                if (nome.isEmpty() || tel.isEmpty() || end.isEmpty() || num.isEmpty() || cidade.isEmpty() || estado.isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos devem ser preenchidos");
                }

                Cliente newCliente = new Cliente(nome, cpf.toString(), tel, end, num, cidade, estado);
                crud.updateCliente(cpf, newCliente);

                outputArea.setText("Cliente alterado com sucesso!\n");
                clearFields();
                cpfAlterarField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.setText("Erro: CPF, Telefone e Número devem ser números válidos.\n");
            } catch (IllegalArgumentException ex) {
                outputArea.setText("Erro: " + ex.getMessage() + "\n");
            }
        }

        private void clearFields() {
            nomeField.setText("");
            cpfField.setText("");
            telField.setText("");
            endField.setText("");
            numField.setText("");
            cidadeField.setText("");
            estadoField.setText("");
        }
    }

    private class RemoveClienteListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Long cpf = Long.valueOf(cpfRemoverField.getText().trim());
                crud.deleteCliente(cpf);

                outputArea.setText("Cliente removido com sucesso!\n");
                cpfRemoverField.setText("");
            } catch (NumberFormatException ex) {
                outputArea.setText("Erro: CPF deve ser um número válido.\n");
            }
        }
    }

    public static void main(String[] args) {
        new ClienteGUI();
    }
}
