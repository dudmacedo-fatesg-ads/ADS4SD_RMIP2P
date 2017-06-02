package br.com.eduardo.fatesg.jrmi;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author eduardo
 */
public class Peer {

    String host;
    int port;
    String pasta;
    String user;
    List<String> peers;
    Registry r;

    IBuscador b;

    public Peer(String host, int port, String pasta, String user, String[] peers) {
        this.host = host;
        this.port = port;
        this.pasta = pasta;
        this.user = user;
        this.peers = Arrays.asList(peers);
    }

    public void mostraMenu() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("********************************");
            System.out.println("*             PEER             *");
            System.out.println("********************************");
            System.out.println("Escolha uma opção:");
            System.out.println();
            System.out.println("1 - Listar Peers Adicionados");
            System.out.println("2 - Adicionar Peer");
//            System.out.println("3 - Buscar arquivo");
            System.out.println();
            System.out.println("0 - Encerrar");
            System.out.println("");

            System.out.print("Opção desejada: ");

            int op = Integer.parseInt(sc.nextLine());
            switch (op) {
                case 1:
                    for (String p : peers) {
                        System.out.println(p);
                    }
                    System.out.println("Pressione qualquer tecla para continuar...");
                    sc.nextLine();
                    break;
                case 2:
                    try {
                        System.out.print("Informe o id do peer: ");
                        String peer = sc.nextLine();
                        if (peer.length() > 0) {
                            b.addBuscador((IBuscador) r.lookup(peer));
                            System.out.println("Peer " + peer + "Adicionado");
                        } else {
                            System.out.println("Voltando ao menu...");
                        }
                        sc.nextLine();
                    } catch (RemoteException | NotBoundException ex) {
                        System.out.println("Erro ao adicionar");
                        sc.nextLine();
//                        ex.printStackTrace();
                    }
                    break;
                case 0:
                    try {
                        r.unbind(user);
                    } catch (RemoteException | NotBoundException ex) {
                        ex.printStackTrace();
                    }
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
            r = LocateRegistry.getRegistry(host, port);

            b = new Buscador(pasta);

            for (String p : peers) {
                b.addBuscador((Buscador) r.lookup(p));
            }

            r.bind(user, b);

        } catch (RemoteException ex) {
//            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (AlreadyBoundException ex) {
//            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        } catch (NotBoundException ex) {
//            Logger.getLogger(Peer.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}
