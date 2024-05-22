import java.util.ArrayList;
import java.util.List;

public class ClienteCRUD {
    private List<Cliente> clientes = new ArrayList<>();

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Cliente> listClientes() {
        return new ArrayList<>(clientes);
    }

    public void updateCliente(Long cpf, Cliente newCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                cliente.setNome(newCliente.getNome());
                cliente.setTel(newCliente.getTel());
                cliente.setEnd(newCliente.getEnd());
                cliente.setNumero(newCliente.getNumero());
                cliente.setCidade(newCliente.getCidade());
                cliente.setEstado(newCliente.getEstado());
                break;
            }
        }
    }

    public void deleteCliente(Long cpf) {
        clientes.removeIf(cliente -> cliente.getCpf().equals(cpf));
    }
}
