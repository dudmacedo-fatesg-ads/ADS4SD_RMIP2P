package br.com.eduardo.fatesg.jrmi;

import java.io.File;
import java.rmi.RemoteException;

/**
 *
 * @author eduardo
 */
public class Test {
    public static void main(String[] args) {
        try {
            Buscador b = new Buscador("/home/eduardo/buscador_rmi/pasta1");
            File f = b.buscar("teste");
            System.out.println(f);
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
}
