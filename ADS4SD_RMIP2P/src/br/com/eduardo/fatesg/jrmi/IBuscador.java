package br.com.eduardo.fatesg.jrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author eduardo
 */
public interface IBuscador extends Remote {
    public boolean buscar(String nome) throws RemoteException;

    public void addBuscador(IBuscador iBuscador) throws RemoteException;
}
