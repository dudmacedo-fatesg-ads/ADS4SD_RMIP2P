package br.com.eduardo.fatesg.jrmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author eduardo
 */
public class RegistryServer {

    private final int port;
    private Registry r;

    public RegistryServer(int port) {
        this.port = port;
    }

    public void mostraMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("********************************");
            System.out.println("*       REGISTRY SERVER        *");
            System.out.println("********************************");
            System.out.println("Escolha uma opção:");
            System.out.println();
            System.out.println("1 - Listar Peers");
            System.out.println();
            System.out.println("0 - Encerrar");
            System.out.println("");

            System.out.print("Opção desejada: ");

            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    try {
                        for (String s : r.list()) {
                            System.out.println(s);
                        }
                    } catch (RemoteException ex) {
//                        Logger.getLogger(RegistryServer.class.getName()).log(Level.SEVERE, null, ex);
                        ex.printStackTrace();
                    }
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    break;
            }
            sc.nextLine();
        }
    }

    public void start() {
        try {
            System.out.println("Iniciando servidor...");

            r = LocateRegistry.createRegistry(port);

            System.out.println("Servidor ativo.");

            r.bind("server", new Starter());
        } catch (Exception e) {
            System.out.println("Ocorreu um problema na inicialização do servidor.\n" + e.toString());
        }
    }

    private class Starter extends UnicastRemoteObject implements Remote {

        Starter() throws RemoteException {

        }
    }
}
