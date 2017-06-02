package br.com.eduardo.fatesg.jrmi;

import br.com.fatesg.buscador.IBuscador;
import java.io.File;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class Buscador extends UnicastRemoteObject implements IBuscador {

    private File pasta;
    private List<IBuscador> buscadores = new ArrayList<>();

    public Buscador(String caminho) throws RemoteException {
        File pasta = new File(caminho);
        if (pasta.exists() && pasta.isDirectory()) {
            this.pasta = pasta;
        } else {
            throw new RemoteException("Pasta inválida");
        }
    }

    public void addBuscador(IBuscador b) throws RemoteException {
        buscadores.add(b);
    }
    
    private File buscar(String nome, File pasta) {
        File[] arqs = pasta.listFiles();
        for (File a : arqs) {
            if (a.isDirectory()) {
                File ret = buscar(nome, a);
                if (ret != null) {
                    return ret;
                }
            }
            else if (a.isFile() && a.getName().contains(nome)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public File buscar(String nome) throws RemoteException {
        File ret = buscar(nome, pasta);
        return ret;
    }

}
