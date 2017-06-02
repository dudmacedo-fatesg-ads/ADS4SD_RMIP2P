package br.com.eduardo.fatesg.jrmi;

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

    @Override
    public void addBuscador(IBuscador b) throws RemoteException {
        buscadores.add(b);
    }

    @Override
    public boolean buscar(String nome) throws RemoteException {
        System.out.println("busca");
        return false;
    }

}
